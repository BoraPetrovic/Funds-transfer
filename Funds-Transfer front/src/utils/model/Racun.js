import React from "react";
import { Table, Button, Form, ButtonGroup } from "react-bootstrap";

import ZavrsniAxios from "../../apis/ZavrsniAxios";

class Racun extends React.Component {
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
      pageNum: 0,
      totalPages: 1,
    };
  }

  componentDidMount() {
    this.getData();
  }

  async getData() {
    await this.getBanke();
    await this.getRacuni();
  }

  async getRacuni(page = null) {
    let config = { params: {} };

    if (this.state.racun.jmbg != "") {
     config.params.jmbg = this.state.racun.jmbg;
    }

    if (this.state.racun.bankaId != -1) {
      config.params.bankaId = this.state.racun.bankaId;
    }

    config.params.iznos = 0;

    if (page != null) {
      config.params.pageNum = page;
    } else {
      config.params.pageNum = this.state.pageNum;
    }

    try {
      let result = await ZavrsniAxios.get("/racuni", config);
      console.log(result);
      if (result && result.status === 200) {
        this.setState({
          racuni: result.data,
          totalPages: result.headers["total-pages"],
        });
      }
    } catch (error) {
      alert("Nije uspelo dobavljanje.");
    }
  }

  async getTipovi(bankaId){
    try {
        let result = await ZavrsniAxios.get("/banke/" + bankaId + "/tipovi");
        console.log(result);
        if (result && result.status === 200) {
          this.setState({
            tipoviRacuna: result.data,
          });
        }
      } catch (error) {
        alert("Nije uspelo dobavljanje.");
      }
  }

  async getBanke() {
    try {
      let result = await ZavrsniAxios.get("/banke");
      console.log(result);
      if (result && result.status === 200) {
        this.setState({
          banke: result.data,
        });
      }
    } catch (error) {
      alert("Nije uspelo dobavljanje.");
    }
  }

  goToEdit(racunId) {
    this.props.history.push("/racuni/edit/" + racunId);
  }

  goPrenost(){
    this.props.history.push("/racuni/prenos");
  }

  async doAdd() {
    try {
      let resault = await ZavrsniAxios.post("/racuni/", this.state.racun);
      let racun = {
        imeIPrezime: "",
        jmbg: "",
        brRacuna: -1,
        stanje: 0,
        tipRacunaId: -1,
        tipRacunaNaziv: "",
        bankaId:-1,
        bankaNaziv:""
      };
      console.log(resault);
      this.setState({ racun: racun });

      this.getRacuni();
    } catch (error) {
      alert("Nije uspelo dodavanje.");
    }
  }

  async doDelete(racunId) {
    try {
      let result = await ZavrsniAxios.delete("/racuni/" + racunId);
      console.log(result);
      this.getRacuni();
    } catch (error) {
      alert("Nije uspelo brisanje.");
    }
  }

  addValueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let racun = this.state.racun;
    racun[name] = value;
    console.log(name + ", " + value);

    if(name == "bankaId"){
        if(value != -1){
            this.getTipovi(value);
        }else{
            this.setState({tipoviRacuna:[]});
        }
    }

    this.setState({ racun: racun });
  }

  doSearch() {
    this.getRacuni();
  }

  doClear(){
    window.location.reload();
  }

  changePage(direction) {
    let page = this.state.pageNum + direction;
    this.getRacuni(page);
    this.setState({ pageNum: page });
  }

  render() {
    return (
      <div>
        <h1>Racun u bankama</h1>

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
            <Form.Label>Banka</Form.Label>
            <Form.Control
              onChange={(event) => this.addValueInputChange(event)}
              name="bankaId"
              value={this.state.racun.bankaId}
              as="select"
            >
              <option value={-1}></option>
              {this.state.banke.map((banka) => {
                return (
                  <option value={banka.id} key={banka.id}>
                    {banka.naziv}
                  </option>
                );
              })}
            </Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Broj racuna</Form.Label>
            <Form.Control
              name="brRacuna"
              onChange={(event) => this.addValueInputChange(event)}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Tip racuna</Form.Label>
            <Form.Control
              onChange={(event) => this.addValueInputChange(event)}
              name="tipRacunaId"
              value={this.state.racun.tipRacunaId}
              as="select"
            >
              <option value={-1}></option>
              {this.state.tipoviRacuna.map((tipRacuna) => {
                return (
                  <option value={tipRacuna.id} key={tipRacuna.id}>
                    {tipRacuna.naziv}
                  </option>
                );
              })}
            </Form.Control>
          </Form.Group>
          <Button variant="primary" onClick={() => this.doAdd()}>
            Dodaj
          </Button>{' '}
          <Button onClick={() => this.doSearch()}>Pretraga</Button>{' '}
          <Button onClick={() => this.doClear()}>Clear</Button>
              </Form>

        <br />
        <br />    
        <ButtonGroup style={{ marginTop: 25, float: 'right' }} >
          <Button 
            disabled={this.state.pageNum == 0}
            onClick={() => this.changePage(-1)}
          >
            Prethodna
          </Button>
          <Button
            disabled={this.state.pageNum + 1 == this.state.totalPages}
            onClick={() => this.changePage(1)}
          >
            Sledeća
          </Button>
        </ButtonGroup>

        <Button 
           
            onClick={() => this.goPrenost()}
            >
            Prenos
          </Button>

        <Table bordered striped style={{ marginTop: 5 }}>
          <thead className="thead-dark">
            <tr>
              <th>Ime i prezime</th>
              <th>JMBG</th>
              <th>Stanje</th>
              <th>Broj racuna</th>
              <th>Tip racuna</th>
              <th>Banka</th>
              <th colSpan={2}>Actions</th>
            </tr>
          </thead>
          <tbody>
            {this.state.racuni.map((racun) => {
              return (
                <tr key={racun.id}>
                  <td>{racun.imeIPrezime}</td>
                  <td>{racun.jmbg}</td>
                  <td>{racun.stanje}</td>
                  <td>{racun.brRacuna}</td>
                  <td>{racun.tipRacunaNaziv}</td>
                  <td>{racun.bankaNaziv}</td>
                  <td>
                    <Button
                      variant="warning"
                      onClick={() => this.goToEdit(racun.id)}
                      style={{ marginLeft: 5 }}
                    >
                      Edit
                    </Button>

                    <Button
                      variant="danger"
                      onClick={() => this.doDelete(racun.id)}
                      style={{ marginLeft: 5 }}
                    >
                      Delete
                    </Button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </Table>
      </div>
    );
  }
}

export default Racun;
