import React from "react";
import { Table, Button, Form, ButtonGroup } from "react-bootstrap";

import ZavrsniAxios from "../../apis/ZavrsniAxios";

class Prenos extends React.Component {

    constructor(props) {
        super(props);

        let racun = {
            brRacunaUplatioca: "",
            brRacunaPrimaoca: "",
            iznos: 0
        };

        this.state = {
            racun: racun,
        };
    }

    addValueInputChange(event) {
        let control = event.target;

        let name = control.name;
        let value = control.value;

        let racun = this.state.racun;
        racun[name] = value;
        console.log(name + ", " + value);

        this.setState({ racun: racun });
    }

    async doPrenos(){
        try {
            let racun = this.state.racun;
            let config = {
                params:{
                    brRacunaUplatioca: racun.brRacunaUplatioca,
                    brRacunaPrimaoca: racun.brRacunaPrimaoca,
                    iznos: racun.iznos
                }}
            let resault = await ZavrsniAxios.get("/racuni", config);
            console.log(resault.data);
            this.props.history.push("/racuni");
          } catch (error) {
            alert("Nije uspeo prenos.");
          }
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
                            name="brRacunaUplatioca"
                            as="input"
                        ></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Broj racuna primaoca</Form.Label>
                        <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name="brRacunaPrimaoca"
                            as="input"
                        ></Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Izons</Form.Label>
                        <Form.Control
                            name="iznos"
                            onChange={(event) => this.addValueInputChange(event)}
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