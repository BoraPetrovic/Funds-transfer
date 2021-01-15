import React from "react";
import {login} from '../services/auth';
import { Button, Form, Container } from 'react-bootstrap';

class Login extends React.Component {
  constructor() {
    super();

    this.state = { username: "", password: "" };
  }

  valueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let change = {};
    change[name] = value;
    
    this.setState(change);
  }

  doLogin(e){
    e.preventDefault();
    login(this.state.username, this.state.password);
  }

  // TODO: Ulepšati: - centrirati, udaljiti od vrha, staviti jumbotron
  // TODO: Završiti implementaciju
  render() {
    return (
      <div>
        <Container>
          <h1>Welcome!</h1>
          <Form>
            <div>
              <Form.Label>Username</Form.Label>
              <Form.Control name="username" onChange={(e) => { this.valueInputChange(e); }}></Form.Control>
            </div>
            <div>
              <Form.Label>Password</Form.Label>
              <Form.Control name="password" type="password" onChange={(e) => { this.valueInputChange(e); }}></Form.Control>
            </div>
            <br />
            <Button onClick={(e) => { this.doLogin(e); }}>Log in</Button>
          </Form>
        </Container>
      </div>
    );
  }
}

export default Login;
