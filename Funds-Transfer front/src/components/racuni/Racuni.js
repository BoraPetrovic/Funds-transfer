import React from "react";
import { Table, Button, Form, ButtonGroup, Collapse } from "react-bootstrap";

import ZavrsniAxios from "../../apis/ZavrsniAxios";

class Racuni extends React.Component {
  constructor(props) {
    super(props);

    let racun = {
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
      racuni: [],
      banke: [],
      tipovi:[],
      search: { jmbgSearch: "", bankaIdSearch: -1 },
      pageNum: 0,
      totalPages: 1,
      show: false
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

    if (this.state.search.jmbgSearch != "") {
      config.params.jmbg = this.state.search.jmbgSearch;
    }

    if (this.state.search.bankaIdSearch != -1) {
      config.params.bankaId = this.state.search.bankaIdSearch;
    }

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

  async doAdd() {
    try {
      let result = await ZavrsniAxios.post("/racuni/", this.state.racun);
      console.log(result);

      let racun = {
        imeIPrezime: "",
        jmbg: "",
        brRacuna: "",
        stanje: "",
        bankaId: "",
        bankaNaziv: "",
        tipRacunaId: "",
        tipRacunaNaziv: ""
    };

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

  async getTipovi(bankaId){
    try {
        let result = await ZavrsniAxios.get("/banke/" + bankaId + "/tipovi-racuna");
        console.log(result);
        if (result && result.status === 200) {
            this.setState({
              tipovi: result.data,
            });
          }
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
            this.setState({show: true});
        } else if(value == -1){
            this.setState({show: false});
        }
        
        this.getTipovi(value);
    } 

    this.setState({ racun: racun });
  }

  searchValueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let search = this.state.search;
    search[name] = value;
    console.log(name + ", " + value);

    this.setState({ search: search });
  }

  doSearch() {
    this.setState({ totalPages: 1, pageNum: 0 });
    this.getRacuni(0);
  }

  changePage(direction) {
    let page = this.state.pageNum + direction;
    this.getRacuni(page);
    this.setState({ pageNum: page });
    //this.setState({pageNum: page}, this.getZadaci);
  }

  doPrenos(){
    this.props.history.push("/racuni/prenos");
  }

  render() {
    return (
      <div>
        <h1>Racuni u bankama</h1>

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
              onChange={(event) => this.addValueInputChange(event)}
              name="brRacuna"
              value={this.state.racun.brRacuna}
              as="input"
            ></Form.Control>
          </Form.Group>
          
          <Collapse in={this.state.show}>
          <Form.Group>
          <Form.Label>Tip racuna</Form.Label>
            <Form.Control
              onChange={(event) => this.addValueInputChange(event)}
              name="tipRacunaId"
              value={this.state.racun.tipRacunaId}
              as="select"
            >
              <option value={-1}></option>
              {this.state.tipovi.map((tip) => {
                return (
                  <option value={tip.id} key={tip.id}>
                    {tip.naziv}
                  </option>
                );
              })}
            </Form.Control>
          </Form.Group>
          </Collapse>
          <Button variant="primary" onClick={() => this.doAdd()}>
            Add
          </Button>
        </Form>

        <Form style={{marginTop:35}}>
          <Form.Group>
            <Form.Label>JMBG</Form.Label>
            <Form.Control
              value={this.state.search.jmbgSearch}
              name="jmbgSearch"
              as="input"
              onChange={(e) => this.searchValueInputChange(e)}
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Banka</Form.Label>
            <Form.Control
              onChange={(event) => this.searchValueInputChange(event)}
              name="bankaIdSearch"
              value={this.state.search.bankaIdSearch}
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
          <Button onClick={() => this.doSearch()}>Pretraga</Button>
        </Form>

        <br />
        <br />

        <Button onClick={() => this.doPrenos()}>
           Prenos
        </Button>

        <ButtonGroup style={{ marginTop: 25, float: 'right' }}>
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
                  <td>{racun.stanje} din.</td>
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

export default Racuni;
