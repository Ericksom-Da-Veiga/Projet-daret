import React,{Component} from "react";
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


const Gestionnaire = () => {

  //variavel para receber os utilisadores do banco de dados
  const [utilisateurs, setutilisateurs] = useState([]);

  const dataAtual = new Date().toISOString().split('T')[0]; // Obtém a data atual no formato 'yyyy-mm-dd'

// variavel utilisador para salvar no banco de dados
  const [user, setuser] = useState({
    nom: '',
    prenom: '',
    mail: '',
    mot_passe: '',
    date_inscription: '',
    roles: 'USER'
  });

// para receber a menssagen do post user
const [message, setmessage] = useState("")

// funçao para atualizar os dados do formulario na variavel user
const Valorform = e => setuser({...user, [e.target.name]: e.target.value})


 //para recuperar todos os utilisadores do banco de dados
 useEffect(()=>{
  fetch("http://localhost:8080/membre/all",{
  method: "GET",
  headers:{
    'Content-Type':'application/json',
  },
})
.then(resp => resp.json())
.then((data) =>{
  setutilisateurs(data)
})
.catch((err)=>console.log(err))
},[])

//funçao para registrar um novo utilisateur
const RegisterUser =async (e) => {
  // para bloquear o recatregamento automatico da pagina
  e.preventDefault();

  const headers = {
    'headers': {
      'Content-Type': 'application/json'
    }
  }

//para enviar os dados do formulario para o meu API back-end dentro da variavel 'user'
 await axios.post('http://localhost:8080/membre', user, headers)
 .then((response) => { //quando receber uma resposta fazer:
    setmessage("Utilisateur ajoute")
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
    {/*Formulaire pour ajouter des nouveua utilisateur*/}
        <Row>
            <Col className="order-xl-1 mt-5" xl="12">
              <Card className="bg-white shadow">
                <CardHeader className="bg-white border-4">
                  <Row className="align-items-center">
                    <Col xs="8">

                      <h2 className="mb-0">Nouvelle Utilisateur</h2>

                      {/*imprimir menssagen retornada do backend*/}
                      {message? <h3>{message}</h3>:""}
                    </Col>
                  </Row>
                </CardHeader>
                <CardBody>

                  {/*formulario*/}
                  <Form onSubmit={RegisterUser}>
                    <Row>
                      <Col lg="6">
                        <FormGroup>
                          <label className="form-control-label" htmlFor="input-username">Nom</label>
                          <Input id="input-username" name="nom" placeholder="Nom" type="text" onChange={Valorform}/>
                        </FormGroup>
                      </Col>
                      <Col lg="6">
                        <FormGroup>
                          <label className="form-control-label" htmlFor="input-email">Prenom</label>
                          <Input id="input-email" placeholder="Prenom" name="prenom" type="text" onChange={Valorform} />
                        </FormGroup>
                      </Col>
                    </Row>
                    <Row>
                      <Col lg="6">
                        <FormGroup>
                          <label className="form-control-label" htmlFor="input-city">Date d'inscription</label>
                          <Input id="input-city" placeholder="Contact" name="date_inscription" min={dataAtual} type="date" onChange={Valorform}/>
                        </FormGroup>
                      </Col>
                      <Col lg="6">
                        <FormGroup>
                          <label className="form-control-label" htmlFor="input-country">Adresse Email</label>
                          <Input id="input-country" placeholder="exemple@gmail.com" name="mail" type="email" onChange={Valorform}/>
                        </FormGroup>
                      </Col>
                    </Row>
                    <Row>
                      <Col lg="6">
                        <FormGroup>
                          <label className="form-control-label" htmlFor="input-city"> Mot de passe</label>
                          <Input id="input-city" placeholder="**********" name="mot_passe" type="password" onChange={Valorform}/>
                        </FormGroup>
                      </Col>
                      <Col lg="6">
                        <FormGroup>
                          <label className="form-control-label" htmlFor="input-country"> Confirmer mot de passe</label>
                          <Input id="input-country" placeholder="**********" type="password" onChange={Valorform}/>
                        </FormGroup>
                      </Col>
                    </Row>
                    <FormGroup>
                  
                      <div className="form-group">
                        <label className="form-control-label" htmlFor="exampleFormControlSelect6"> Roles </label>
                        <select name="roles" id="exampleFormControlSelect6" className="form-control" defaultValue="USER" onChange={Valorform}>
                          <option value="USER" >Participant</option>
                          <option value="ADMIN">Administracteur</option>
                        </select>
                      </div>
                    </FormGroup>
                    <Row>
                      <Col lg="4">
                        <Button color="info" type="submit"> Ajouter</Button>
                      </Col>
                    </Row>
                  </Form>
                </CardBody>
              </Card>
            </Col>
        </Row>


{/*Liste des Utilisateur*/}
        <Row className="my-5">
          <Col className="mb-5 mb-xl-0" xl="12">
            <Card className="shadow">
              <CardHeader className="border-0">
                <Row className="align-items-center">
                  <div className="col">
                    <h3 className="mb-0">Liste des Utilisateur</h3>
                  </div>
                </Row>
              </CardHeader>
              <Table className="align-items-center table-flush" responsive>
                <thead className="thead-light">
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nom et Prenoms</th>
                    <th scope="col">Email</th>
                    <th scope="col">Role</th>
                    <th scope="col">Status</th>
                    <th scope="col">Date inscription</th>
                  </tr>
                </thead>
                <tbody>
                    {utilisateurs.length > 0 && 
                    utilisateurs.map((utilisateur)=>{
                      // Formata a data de inscrição
                      const dateArray = utilisateur.date_debut;
                    const formattedDate =
                      dateArray && dateArray.length === 3
                        ? new Date(dateArray[0], dateArray[1] - 1, dateArray[2]).toLocaleDateString()
                        : 'Data de inscrição não disponível';  
                      return(
                      <tr>
                        <td>{utilisateur.id}</td>
                        <th scope="row">{utilisateur.nom} {utilisateur.prenom}</th>
                        <td>{utilisateur.mail}</td>
                        <td>{utilisateur.role}</td>
                        <td><i className="ni ni-check-bold text-success mr-3" />{utilisateur.activer} </td>
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

export default Gestionnaire;