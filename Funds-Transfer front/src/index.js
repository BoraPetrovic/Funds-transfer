import React from 'react';
import ReactDOM from 'react-dom';
import { Button, Container, Nav, Navbar } from 'react-bootstrap';
import {HashRouter as Router, Link, Route, Switch, Redirect } from 'react-router-dom';
import Home from './components/Home';
import NotFound from './components/NotFound';
import Login from './authentication/Login';
import { logout } from './services/auth';
import Racuni from './components/racuni/Racuni';
import EditRacun from './components/racuni/EditRacun';
import Prenos from './components/racuni/Prenos';

class App extends React.Component{
    render(){
        let token = window.localStorage.getItem("token");
        if(token){
            return (
                <div>
                    <Router>
                        <Navbar bg="dark" variant="dark">
                            <Navbar.Brand>
                                <Link to="/">Banka</Link>
                            </Navbar.Brand>
                            <Nav className="mr-auto">
                                <Nav.Link as={Link} to="/racuni">
                                    Racuni
                                </Nav.Link>
                            </Nav>
                            <Button onClick={()=>logout()}>Log out</Button>
                        </Navbar>
    
                        <Container style={{marginTop:25}}>
                            <Switch>
                                <Route exact path="/" component={Home}></Route>
                                <Route exact path="/racuni" component={Racuni}></Route>
                                <Route exact path="/racuni/edit/:id" component={EditRacun}></Route>
                                <Route exact path="/racuni/prenos" component={Prenos}></Route>
                                

                                <Route exact path="/login" render={()=><Redirect to="/" />} />

                                <Route component={NotFound}></Route>
                            </Switch>
                        </Container>    
                    </Router>
                </div>)
        }else{
            return (
                <div>
                    <Container>
                        <Router>
                            <Switch>
                                <Route exact path="/login" component={Login} />
                                <Route render={() => <Redirect to="/login" />} />
                            </Switch>
                        </Router>
                    </Container>
                </div>
            )

        }  
    }
}

ReactDOM.render(<App />,document.querySelector("#root"));