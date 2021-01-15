import React from "react";
import { Button, Form } from "react-bootstrap";

import ZavrsniAxios from "../../apis/ZavrsniAxios";

class EditRacun extends React.Component {
  constructor(props) {
    super(props);

    let racun = {
        id: this.props.match.params.id,
        imeIPrezime: "",
        jmbg: "",
        brRacuna: "",
        stanje: "",
        bankaId: "",
        bankaNaziv: "",
        tipRacunaId: "",
        tipRacunaNaziv: ""
    };

    this.state = {
        racun: racun,
      };
  }

  componentDidMount() {
    this.getData();
  }

  async getData() {
    await this.getRacun();
  }

  async getRacun() {
    
    try {
      let result = await ZavrsniAxios.get("/racuni/" + this.props.match.params.id);
      console.log(result)
      if (result && result.status === 200) {
        this.setState({
          racun: result.data
        });
      }
    } catch (error) {
      alert("Nije uspelo dobavljanje.");
    }
  }

  async doEdit() {
    try {
      let result = await ZavrsniAxios.put("/racuni/" + this.props.match.params.id, this.state.racun);
      console.log(result)
      this.props.history.push("/racuni");
    } catch (error) {
      alert("Nije uspelo čuvanje.");
    }
  }

  valueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let racun = this.state.racun;
    racun[name] = value;
    console.log(name + ", " + value)

    this.setState({ racun: racun });
  }

  render() {
    return (
      <div>
        <h1>Edit racun</h1>

        <Form>
          <Form.Group>
            <Form.Label>Ime i prezime</Form.Label>
            <Form.Control
              onChange={(event) => this.valueInputChange(event)}
              name="imeIPrezime"
              value={this.state.racun.imeIPrezime}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>JMBG</Form.Label>
            <Form.Control
              onChange={(event) => this.valueInputChange(event)}
              name="jmbg"
              value={this.state.racun.jmbg}
              as="input"
            ></Form.Control>
          </Form.Group>

          
          <Form.Group>
            <Form.Label>Broj racuna</Form.Label>
            <Form.Control
              onChange={(event) => this.valueInputChange(event)}
              name="brRacuna"
              value={this.state.racun.brRacuna}
              as="input"
            ></Form.Control>
          </Form.Group>
          
          <Form.Group>
            <Form.Label>Stanje</Form.Label>
            <Form.Control
              onChange={(event) => this.valueInputChange(event)}
              name="stanje"
              value={this.state.racun.stanje}
              as="input"
            ></Form.Control>
          </Form.Group>
          
          <Button variant="primary" onClick={() => this.doEdit()}>
            Edituj
          </Button>
        </Form>

      </div>
    );
  }
}

export default EditRacun;
