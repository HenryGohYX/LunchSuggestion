import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { useCookies } from 'react-cookie';

const LunchEdit = () => {
  const initialFormState = {
    name: '',
    location: '',
  };
  const [location, setLocation] = useState(initialFormState);
  const navigate = useNavigate();
  const { id } = useParams();
  const [cookies] = useCookies(['XSRF-TOKEN']);

  const isAddLocation = id === 'add';
  useEffect(() => {
    if (!isAddLocation) {
      fetch(`/api/lunch/suggestion/${id}`)
        .then(response => response.json())
        .then(data => setLocation(data.lunchLocation));
    }
  }, [id, setLocation]);

  const handleChange = (event) => {
    const { name, value } = event.target

    setLocation({ ...location, [name]: value })
  }

  const handleSubmit = async (event) => {
    event.preventDefault();

    await fetch(`/api/lunch/suggestion${location.id ? `/${location.id}` : ''}`, {
      method: (location.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(location)
    });
    setLocation(initialFormState);
    navigate('/lunch-menu');
  }

  const title = <h2>{ isAddLocation ? 'Add Location' : 'Edit Location' }</h2>;

  return (<div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={handleSubmit}>
          {isAddLocation && (
          <FormGroup>
            <Label for="name">Name</Label>
            <Input type="text" name="name" id="name" value={location.name || ''}
                       onChange={handleChange} autoComplete="name-level1" required />
          </FormGroup>)}
          <FormGroup>
            <Label for="location">Location</Label>
            <Input type="text" name="location" id="location" value={location.location || ''}
                       onChange={handleChange} autoComplete="location-level1" required />
          </FormGroup>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/lunch-menu">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  )
};

export default LunchEdit;