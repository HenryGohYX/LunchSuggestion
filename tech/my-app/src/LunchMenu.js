import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

const LunchMenu = () => {

  const [location, setLocation] = useState([]);
  const [loading, setLoading] = useState(false);
  const [modalOpen, setModalOpen] = useState(false);
  const [randomLocation, setRandomLocation] = useState(null);

  useEffect(() => {
    setLoading(true);

    fetch('api/lunch/suggestions')
      .then(response => response.json())
      .then(data => {
        setLocation(data.lunchLocationList);
        setLoading(false);
      })
  }, []);

  const remove = async (id) => {
    await fetch(`/api/lunch/suggestion/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedLocations = [...location].filter(i => i.id !== id);
      setLocation(updatedLocations);
    });
  }

  if (loading) {
    return <p>Loading...</p>;
  }

  const lunchMenu = location.map(group => {
    return <tr key={group.id}>
      <td>{group.name}</td>
      <td>{group.location}</td>
      <td>
       <ButtonGroup>
        <Button size="sm" color="primary" style={{ borderRadius: '5px', width: '60px' }} tag={Link} to={"/lunch-edit/" + group.id}>Edit</Button>
        <Button size="sm" color="danger" style={{ borderRadius: '5px', width: '60px', marginLeft: '5px' }} onClick={() => remove(group.id)}>Delete</Button>
       </ButtonGroup>
      </td>
    </tr>
  });

  const toggleModal = () => {
      setModalOpen(!modalOpen);
  };

  const fetchDataFromApi = () => {
    fetch('api/lunch/suggestion/random')
      .then(response => response.json())
      .then(data => {
        setRandomLocation(data);
      })
      .catch(error => {
        console.error('Error fetching data: ', error);
      });
  };

  return (
    <div>
      <AppNavbar/>
      <Container fluid>
        <div className="float-end">
         <Button color="success" onClick={() => { toggleModal(); fetchDataFromApi(); }}>Get Location</Button>
            <Modal isOpen={modalOpen} toggle={toggleModal}>
             <ModalHeader>Govtech Lunch Roulette has spoken!</ModalHeader>
             <ModalBody>
               {randomLocation ? (
                 <div>
                   {randomLocation.lunchLocation ? (
                     <div>
                       <p>Award Winner: {randomLocation.lunchLocation.name}</p>
                       <p>Lucky Location: {randomLocation.lunchLocation.location}</p>
                     </div>
                   ) : (
                     <p>No lunch location available to suggest...</p>
                   )}
                 </div>
               ) : (
                 <p>Loading data...</p>
               )}
             </ModalBody>
             <ModalFooter>
               <Button color="secondary" onClick={toggleModal}>Close</Button>
             </ModalFooter>
            </Modal>

         <Button color="success" tag={Link} to="/lunch-edit/add" style={{ marginLeft: '10px' }}>Add Location</Button>
        </div>
        <h3>Govtech Lunch Menu</h3>
        <Table className="mt-4">
          <thead>
          <tr>
            <th width="20%">Name</th>
            <th width="55%">Location</th>
            <th width="25%">Options</th>
          </tr>
          </thead>
          <tbody>
          {lunchMenu}
          </tbody>
        </Table>
      </Container>
    </div>
  );
};

export default LunchMenu;