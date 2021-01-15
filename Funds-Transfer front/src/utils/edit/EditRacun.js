import React from "react";
import { Button, Form } from "react-bootstrap";

import ZavrsniAxios from "../../apis/ZavrsniAxios";

class EditRacun extends React.Component {
  constructor(props) {
    super(props);

    let racun = {
        imeIPrezime: "",
        jmbg: "",
        brRacuna: -1,
        stanje: -1,
        tipRacunaId: -1,
        tipRacunaNaziv: "",
        bankaId:-1,
        bankaNaziv:""
      };

      this.state = {
        racun: racun,
        racuni: [],
        banke: [],
        tipoviRacuna:[],
      };
  }

  componentDidMount() {
    this.getData();
  }

  async getData() {
    await this.getBanke();
    await this.getRacuni();
  }

  async getRacuni() {
    
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

  async getBanke() {
    try {
      let result = await ZavrsniAxios.get("/banke");
      console.log(result)
      if (result && result.status === 200) {
        this.setState({
          banke: result.data,
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

  addValueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let racun = this.state.racun;
    racun[name] = value;
    console.log(name + ", " + value)

    if(name == "bankaId"){
        if(value != -1){
            this.getTipovi(value);
        }else{
            this.setState({tipoviRacuna:[]});
        }
    }

    this.setState({ racun: racun });
  }

  async getTipovi(bankaId){
    try {
        let result = await ZavrsniAxios.get("/banke/" + bankaId + "/tipovi");
        console.log(result)
        if (result && result.status === 200) {
          this.setState({
            tipoviRacuna: result.data,
          });
        }
      } catch (error) {
        alert("Nije uspelo dobavljanje.");
      }
  }

  render() {
    return (
      <div>
        <h1>Edit Zadatak</h1>

        <Form>
          <Form.Group>
            <Form.Label>Ime i prezime</Form.Label>
            <Form.Control
              onChange={(event) => this.addValueInputChange(event)}
              name="imeIPrezime"
              value={this.state.racun.imeIPrezime}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>JMBG</Form.Label>
            <Form.Control
              onChange={(event) => this.addValueInputChange(event)}
              name="jmbg"
              value={this.state.racun.jmbg}
              as="input"
            ></Form.Control>
          </Form.Group>       
          <Form.Group>
            <Form.Label>Broj racuna</Form.Label>
            <Form.Control
              name="brRacuna"
              value={this.state.racun.brRacuna}
              onChange={(event) => this.addValueInputChange(event)}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Stanje racuna</Form.Label>
            <Form.Control
              name="stanje"
              value={this.state.racun.stanje}
              onChange={(event) => this.addValueInputChange(event)}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Button variant="primary" onClick={() => this.doEdit()}>
            Edit
          </Button>
        </Form>
      </div>
    );
  }
}

export default EditRacun;
