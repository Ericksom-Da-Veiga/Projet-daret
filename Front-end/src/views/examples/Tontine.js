import React from "react";
import {useState,useEffect} from 'react';
import axios from 'axios';
import {
  Container,
  Row,
  Col,
  Card,
  CardHeader,
  CardBody,
  Table,
  FormGroup,
  Label,
  Form,
  Input,
  Button,
  Breadcrumb,
  BreadcrumbItem,
  CardTitle
} from 'reactstrap';





const Tontine = () => {

  //pour recuperer les tontines/darets
  const [tontines, settontines] = useState([]);

  const [message,setmessage]= useState("")

  useEffect(()=>{
    fetch("http://localhost:8080/darets",{
    method: "GET",
    headers:{
      'Content-Type':'application/json',
    },
  })
  .then(resp => resp.json())
  .then((data) =>{
    settontines(data)
  })
  .catch((err)=>console.log(err))
  },[])

  //pour sauvegarder les tontines
  const [posttontine, setposttontine] = useState({
    nom:'',
    description:'',
    date_debut:'',
    periodicite: '',
    maximun_participant: '',
    activer: true,
    montant: ''
  })
  //pour actualiser les valeurs des tontines avec des valeurs mis dans le formulaire
  const Valorform = e => setposttontine({...posttontine, [e.target.name]: e.target.value})

  const Register = async(e)=>{
    e.preventDefault();

    const headers = {
      'headers': {
        'Content-Type': 'application/json'
      }
    }

    console.log(posttontine)
  
  //para enviar os dados do formulario para o meu API back-end dentro da variavel 'posttontine'
   await axios.post('http://localhost:8080/darets', posttontine, headers)
   .then((response) => { //quando receber uma resposta fazer:
      setmessage("tontine crie")
   }).catch((err) =>{ //quando a api retornar algun erro
    if (err.response.data.errorMessage) {
       // Se a resposta contém uma mensagem de erro do servidor
       setmessage(err.response.data.errorMessage);
       console.error("dentro do if:", message);
    }else{
        // Se a resposta não contém uma mensagem de erro específica, use uma mensagem padrão
        console.error("Erro no envio para a API:", err);
    }
   })
  }
  return (
    <>

      <div className="header bg-gradient-info pb-8 pt-5 pt-md-8"  style={{ height: '325px' }}>
      </div>

      <Container style={{ height: '325px', position: 'absolute', top: '80px' }} fluid>
{/*formulaire pou ajouter une tontine*/}
        <Row>
            <Col className="order-xl-1 mt-5" xl="12">
              <Card className="bg-white shadow">
                <CardHeader className="bg-white border-4">
                  <Row className="align-items-center">
                    <Col xs="8">
                      <h3 className="mb-0">Nouvelle Tontine</h3>
                    </Col>
                  </Row>
                </CardHeader>
                <CardBody>
                  <Form onSubmit={Register}>
                    <Row>
                      <Col lg="6">
                        <FormGroup>
                          <label className="form-control-label" htmlFor="input-username">Nom Tontine</label>
                          <Input
                            id="input-username"
                            placeholder="Nom Tontine"
                            type="text"
                            name="nom"
                            onChange={Valorform}
                          />
                        </FormGroup>
                      </Col>
                      <Col lg="6">
                        <FormGroup>
                          <label
                            className="form-control-label"
                            htmlFor="input-email"
                          >
                            Nombre maximun de membre
                          </label>
                          <Input
                            id="input-email"
                            placeholder="Nombre maximun de membre"
                            type="number"
                            name="maximun_participant"
                            onChange={Valorform}
                          />
                        </FormGroup>
                      </Col>
                    </Row>
                    <Row>
                      <Col lg="4">
                        <FormGroup>
                          <label
                            className="form-control-label"
                            htmlFor="input-city"
                          >
                            Montant Maximun
                          </label>
                          <Input
                            id="input-city"
                            placeholder="Montant Maximun"
                            type="number"
                            name="montant"
                            onChange={Valorform}
                          />
                        </FormGroup>
                      </Col>
                      <Col lg="4">
                        <FormGroup>
                          <label
                            className="form-control-label"
                            htmlFor="input-country"
                          >
                            Periodicite
                          </label>
                          <Input
                            id="input-country"
                            placeholder="Nombre de Cycle"
                            type="number"
                            name="periodicite"
                            onChange={Valorform}
                          />
                        </FormGroup>
                      </Col>
                      <Col lg="4">
                        <FormGroup>
                          <label
                            className="form-control-label"
                            htmlFor="input-country"
                          >
                            Date debut
                          </label>
                          <Input
                            id="input-postal-code"
                            placeholder="Marge"
                            type="date"
                            name="date_debut"
                            onChange={Valorform}
                          />
                        </FormGroup>
                      </Col>
                    </Row>
                    <FormGroup>
                      <label>Description</label>
                      <Input
                        placeholder="description..."
                        rows="4"
                        type="textarea"
                        name="description"
                            onChange={Valorform}
                      />
                    </FormGroup>
                    <Row>
                      <Col lg="4">
                        <Button color="info" type="submit">
                          Ajouter
                        </Button>
                      </Col>
                    </Row>
                  </Form>
                </CardBody>
              </Card>
            </Col>
        </Row>
{/*Liste des tontines*/}
        <Row className="my-5">
          <Col className="mb-5 mb-xl-0" xl="12">
            <Card className="shadow">
              <CardHeader className="border-0">
                <Row className="align-items-center">
                  <div className="col">
                    <h3 className="mb-0">Dernier Membres</h3>
                  </div>
                </Row>
              </CardHeader>
              <Table className="align-items-center table-flush" responsive>
                <thead className="thead-light">
                  <tr>
                    <th scope="col">N</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Nb Membre</th>
                    <th scope="col">Montant Total</th>
                    <th scope="col">Periodicite</th>
                    <th scope="col">Date Debut</th>
                  </tr>
                </thead>
                <tbody>
                    {tontines.length > 0 && tontines.map((tontine)=>{
                        // Formata a data de inscrição
                        const dateArray = tontine.date_debut;
                        const formattedDate = 
                        dateArray && dateArray.length === 3
                            ? new Date(dateArray[0], dateArray[1] - 1, dateArray[2]).toLocaleDateString()
                            : 'Data de inscrição não disponível'; 
                        
                          return(
                            <tr>
                              <td>{tontine.id}</td>
                              <th scope="row">{tontine.nom}</th>
                              <td>{tontine.maximun_participant}</td>
                              <td>{tontine.montant} MAD</td>
                              <td>{tontine.periodicite} semaines</td>
                              <td>{formattedDate}</td>
                          </tr>
                          )
                      })}
                </tbody>
              </Table>
            </Card>
          </Col>
        </Row>
        
        <br/>
      </Container>
      
    </>
  );
};

export default Tontine;
