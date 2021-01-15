import React from 'react';
import { Button, Table, Form, Collapse } from "react-bootstrap";
import SprintAxios from '../../apis/SprintAxios';

class Zadaci extends React.Component {

    constructor(props){
        super(props);

        let zadatak = {
            ime: null,
            zaduzeni: "",
            bodovi: -1,
            sprint: null,
            stanje: null
        } 

        let search = {
            imeSearch: "",
            sprintIdSearch: ""
        }


        this.state = {zadaci: [], search: search, ukupnoBodova:"", zadatak: zadatak, stanja: [], sprintovi: [], pageNum: 0, totalPages: 1, sledeceStanje: false, sprintShow:false, searchShow: false}
    } 

    componentDidMount(){
        this.getZadaci();
        this.getStanja();
        this.getSprintovi();
    }

    async getSprintovi(){
        try{
            let result = await SprintAxios.get("/sprintovi");
            console.log(result.data);
            this.setState({sprintovi: result.data});
        }catch(error){
            console.log(error);
            alert("Couldn't fetch stanja!");
        }
    }

    async getStanja(){
        try{
            let result = await SprintAxios.get("/stanja");
            console.log(result.data);
            this.setState({stanja: result.data});
        }catch(error){
            console.log(error);
            alert("Couldn't fetch stanja!");
        }
    }

    async getZadaci(){
        try{
            let config ={params:{}}
            if(this.state.search.imeSearch != ""){
                config.params.ime = this.state.search.imeSearch;
            }
            if(this.state.search.sprintIdSearch != ""){
                config.params.sprintId = this.state.search.sprintIdSearch;
            }

            config.params.pageNum = this.state.pageNum;

            let result = await SprintAxios.get("/zadaci", config);
            console.log(result);
            this.setState({zadaci: result.data, totalPages: result.headers["total-pages"]});            
        }catch(error){
            console.log(error);
            alert("Couldn't fetch zadaci!");
        }
        
        
    }

    getStanje(list){
        return list.map((stanje) => stanje.ime);
    }

    goToEdit(zadatakId){
        this.props.history.push("/zadaci/edit/" + zadatakId);
    }

    async delete(zadatakId){
        try{
            await SprintAxios.delete("/zadaci/" + zadatakId);
            window.location.reload();
        }catch(error){
            console.log(error);
            alert("Couldn't delete!");
        }
    }

    async getOne(zadatakId){
        
        try{
            let config ={
                params:{
                    promenaStanja: true
                }
                
            }
            let result = await SprintAxios.get("/zadaci/" + zadatakId, config);
            console.log(result.data);
            this.setState({sledeceStanje: false});
            console.log(this.state.sledeceStanje)
            window.location.reload();       
        }catch(error){
            console.log(error);
            alert("Couldn't change stanje!");
        }
        
    }

    getSledeceStanje(stanjeId){
        this.setState({sledeceStanje: true});
       this.getOne(stanjeId)                         
    }

    renderZadatak(){
        return this.state.zadaci.map((zadatak) => {
            return (
                <tr  key={zadatak.id}>
                    <td>{zadatak.ime}</td>
                    <td>{zadatak.zaduzeni}</td>
                    <td>{zadatak.bodovi}</td>
                    <td>{zadatak.stanjeDTO.ime}</td>
                    <td>{zadatak.sprintDTO.ime}</td>
                    <td>
                        <Button onClick={()=> this.getSledeceStanje(zadatak.id)} disabled={zadatak.stanjeDTO.ime == "Zavrsen"}>Preci na sledece stanje</Button>{' '}
                        <Button variant="warning" onClick={() => this.goToEdit(zadatak.id)}>Edit</Button>{' '}
                        <Button variant="danger" onClick={() => this.delete(zadatak.id)}>Delete</Button>
                    </td>
                </tr>
            );
        });
    }

    getOnChangeValue(e){
        let input = e.target;

        let name = input.name;
        let value = input.value;

        let zadatak = this.state.zadatak;
        zadatak[name] = value;

        console.log(name + ", " + value);
        this.setState({zadatak: zadatak});
    }

    getSprintOnChange(e){
        let input = e.target;

        let sprintid = input.value;
        let sprint = this.state.sprintovi.find((sprintDTO) => sprintDTO.id == sprintid);

        console.log(sprintid + ", " + sprint.ime);

        let zadatak = this.state.zadatak;
        zadatak.sprint = sprint;

        this.setState({zadatak:zadatak});
    }

    getStanjeOnChange(e){
        let input = e.target;

        let stanjeId = input.value;
        let stanje = this.state.stanja.find((stanjeDTO) => stanjeDTO.id == stanjeId);

        console.log(stanjeId + ", " + stanje.ime);

        let zadatak = this.state.zadatak;
        zadatak.stanje = stanje;

        this.setState({zadatak: zadatak});
    }

    async add(){
        try{
            let zadatak = this.state.zadatak;
            let config = {
                    ime: zadatak.ime,
                    zaduzeni: zadatak.zaduzeni,
                    bodovi: zadatak.bodovi,
                    sprintDTO: zadatak.sprint,
                    stanjeDTO: zadatak.stanje
                
            }

            let result = await SprintAxios.post("/zadaci", config);
            console.log(result.data);
            this.getZadaci();
        }catch(error){
            console.log(error);
            alert("Couldn't fetch zadaci!");
        }

    }

    changePage(page){
        let pageNum = this.state.pageNum;
        pageNum = pageNum + page;

        this.setState({pageNum: pageNum}, ()=>this.getZadaci());
        
    }

    getOnChangeValueSearch(e){
        let input = e.target;

        let name = input.name;
        let value = input.value;

        let search = this.state.search;
        search[name] = value;

        console.log(name + ", " + value);
        this.setState({search: search});
    }

    getPretraga(e){
        e.preventDefault();
        this.getUkupnoBodova();
        this.setState({pageNum: 0}, ()=>{this.getZadaci();});
    }

    getUkupnoBodova(){
        
        if(this.state.search.sprintIdSearch != ""){
            let sprint= this.state.sprintovi.find((sprint) => sprint.id == this.state.search.sprintIdSearch);
            this.setState({ukupnoBodova: sprint.ukupnoBodova, sprintShow:true});
        }else{
            this.setState({ukupnoBodova: "", sprintShow: false});
        }
    }

    render(){
        return(
            <div>
                
                <br />
                <br />

                <Form>
                    <Form.Group>
                        <Form.Label>Ime</Form.Label>
                        <Form.Control onChange={(e) => this.getOnChangeValue(e)} name="ime"></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Zaduzeni</Form.Label>
                        <Form.Control onChange={(e) => this.getOnChangeValue(e)} name="zaduzeni"></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Bodovi</Form.Label>
                        <Form.Control onChange={(e) => this.getOnChangeValue(e)} name="bodovi"></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Sprint</Form.Label>
                        <Form.Control onChange={(e) => this.getSprintOnChange(e)} as="select" name="sprintId">
                            <option value=""></option>
                            {
                                this.state.sprintovi.map((sprint) => {
                                    return(<option key={sprint.id} value={sprint.id}>{sprint.ime}</option>);
                                })
                            }
                        </Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Stanje</Form.Label>
                        <Form.Control onChange={(e) => this.getStanjeOnChange(e)} as="select" name="stanjeId">
                            <option value=""></option>
                            {
                                this.state.stanja.map((stanje)=>{
                                return(<option key={stanje.id} value={stanje.id}>{stanje.ime}</option>)
                                })
                            }
                        </Form.Control>
                    </Form.Group>
                    <Button onClick={() => this.add()}>Dodaj</Button>
                </Form>

                <br />
                <br />

                <Form>
                    <Form.Group>
                        <Form.Check type="checkbox" onClick={() => {this.setState({searchShow: !this.state.searchShow}) }} 
                            label="Prikazi formu za pretragu"
                            aria-controls="collapse-serach"
                            aria-expanded={this.state.searchShow}></Form.Check>
                    </Form.Group>
                </Form>
                <Collapse in={this.state.searchShow}>
                    <div id="collapse-search">
                        <Form>
                            <Form.Label htmlFor="imeSearch">Ime zadatka</Form.Label>
                            <Form.Control name="imeSearch" onChange={(e) => this.getOnChangeValueSearch(e)}></Form.Control>
                            <Form.Label htmlFor="sprintIdSearch">Sprint</Form.Label>
                            <Form.Control name="sprintIdSearch" as="select" onClick={(e)=>this.getOnChangeValueSearch(e)}>
                                <option value=""></option>
                                {
                                    this.state.sprintovi.map((sprint) => {
                                        return (
                                        <option key={sprint.id} value={sprint.id}>{sprint.ime}</option>
                                        )
                                    })
                                }
                            </Form.Control>
                            <br />
                            <Button onClick={(e) => this.getPretraga(e)}>Search</Button>
                        </Form>
                    </div>
                
                </Collapse>
                <br />
                <br />
                <div>
                    <div style={{ float: 'right' }}>
                        <Button variant="success" onClick={()=> this.changePage(-1)} disabled={this.state.pageNum == 0}>Predhodna</Button>
                        <Button variant="success" onClick={() => this.changePage(1)} disabled={this.state.pageNum == this.state.totalPages - 1}>Sledeca</Button>
                    </div>
                    <Table striped bordered>
                        <thead style={{ backgroundColor: 'black' }}>
                            <tr>
                                <th style={{ color: 'white' }}>Ime</th>
                                <th style={{ color: 'white' }}>Zaduzeni</th>
                                <th style={{ color: 'white' }}>Bodovi</th>
                                <th style={{ color: 'white' }}>Stanje</th>
                                <th style={{ color: 'white' }}>Sprint</th>
                                <th style={{ color: 'white' }}>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.renderZadatak()}
                        </tbody>
                    </Table>
                    <Collapse in={this.state.sprintShow}>
                        <div id="collapse-search">

                                Ukupno bodova po sprintu: {this.state.ukupnoBodova}

                        </div>
                    </Collapse>
                    <br />
                    <br />
                    <br />
                </div>
            </div>
        );
        
    }
}

export default Zadaci;