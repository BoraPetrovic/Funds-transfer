import React from 'react';
import { Button, Form } from "react-bootstrap";
import ZavrsniAxios from './../../apis/ZavrsniAxios';

class Prenos extends React.Component {

    constructor(props) {
        super(props);

        let prenos = {
            uplatioc: "",
            primalac: "",
            iznos: "",
        }

        this.state = {
            prenos: prenos
        };
    }

    async doPrenos() {
        let config = { params: {} };

        if (this.state.prenos.uplatioc != "") {
            config.params.uplatioc = this.state.prenos.uplatioc;
        }

        if (this.state.prenos.primalac != "") {
            config.params.primalac = this.state.prenos.primalac;
        }

        if (this.state.prenos.iznos != "") {
            config.params.iznos = this.state.prenos.iznos;
        }

        try {
            let result = await ZavrsniAxios.get("/racuni/prenos", config);
            console.log(result);
            if (result.status === 200) {
                alert("Prenos izvrsen");
                this.props.history.push("/racuni");
            } else if(result.status === 406){
                alert("Nemate dovoljno sredstava");
            }
        } catch (error) {
            alert("Nije uspelo dobavljanje.");
        }
    }

    addValueInputChange(event) {
        let control = event.target;

        let name = control.name;
        let value = control.value;

        let prenos = this.state.prenos;
        prenos[name] = value;
        console.log(name + ", " + value);

        this.setState({ prenos: prenos });
    }


    render() {
        return (
            <div>
                <h1>Nalog za prenos</h1>
                <Form>
                    <Form.Group>
                        <Form.Label>Broj racuna uplatioca</Form.Label>
                        <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name="uplatioc"
                            value={this.state.prenos.uplatioc}
                            as="input"
                        ></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Broj racuna primaoca</Form.Label>
                        <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name="primalac"
                            value={this.state.prenos.primalac}
                            as="input"
                        ></Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Iznos</Form.Label>
                        <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name="iznos"
                            value={this.state.prenos.iznos}
                            as="input"
                        ></Form.Control>
                    </Form.Group>

                    <Button variant="primary" onClick={() => this.doPrenos()}>
                        Prenesi
          </Button>
                </Form>
            </div>
        );
    }
}

export default Prenos;