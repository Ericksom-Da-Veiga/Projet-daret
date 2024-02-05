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

const Paymment = () => {

  const dataAtual = new Date().toISOString().split('T')[0]; // Obtém a data atual no formato 'yyyy-mm-dd'

 //pour recuperer les tontines/darets
 const [tontines, settontines] = useState([]);
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



//pour recuperer les participant
const [utilisateurs, setutilisateurs] = useState([]);

useEffect(()=>{
  fetch("http://localhost:8080/membre",{
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

//pour recuperer les administracteur
const [admin, setadmin] = useState([]);
useEffect(()=>{
  fetch("http://localhost:8080/versement/admin",{
  method: "GET",
  headers:{
    'Content-Type':'application/json',
  },
})
.then(resp => resp.json())
.then((data) =>{
  setadmin(data)
})
.catch((err)=>console.log(err))
},[])

//pouyr recuperer les payements
  const [Payments, SetPayements] = useState([]);
  useEffect(()=>{
    fetch("http://localhost:8080/payements",{
    method: "GET",
    headers:{
      'Content-Type':'application/json',
    },
  })
  .then(resp => resp.json())
  .then((data) =>{
    SetPayements(data)
  })
  .catch((err)=>console.log(err))
  },[])


  // Pours sauvegarder le spayements
  const [message,setmessage]= useState("")

  const [postpayement, setpostpayement] = useState({
    id_membre:"",
    id_admin:"",
    id_daret:"",
    date_payement:"1"
  })
  //pour actualiser les valeurs des tontines avec des valeurs mis dans le formulaire
  const Valorform = e => setpostpayement({...postpayement, [e.target.name]: e.target.value})

  const Register = async(e)=>{
    e.preventDefault();

    const headers = {
      'headers': {
        'Content-Type': 'application/json'
      }
    }

    console.log(postpayement)
  
  //para enviar os dados do formulario para o meu API back-end dentro da variavel 'posttontine'
  //  await axios.post('http://localhost:8080/payements', postpayement, headers)
  //  .then((response) => { //quando receber uma resposta fazer:
  //     setmessage("Payement Ajoute")
  //  }).catch((err) =>{ //quando a api retornar algun erro
  //   if (err.response.data.errorMessage) {
  //      // Se a resposta contém uma mensagem de erro do servidor
  //      setmessage(err.response.data.errorMessage);
  //      console.error("dentro do if:", message);
  //   }else{
  //       // Se a resposta não contém uma mensagem de erro específica, use uma mensagem padrão
  //       console.error("Erro no envio para a API:", err);
  //   }
  //  })
  }


  return (
    <>

      <div className="header bg-gradient-info pb-8 pt-5 pt-md-8"  style={{ height: '325px' }}>
      </div>

      <Container style={{ height: '325px', position: 'absolute', top: '80px' }} fluid>

        <Row>
            <Col className="order-xl-1 mt-5" xl="12">
              <Card className="bg-white shadow">
                <CardHeader className="bg-white border-4">
                  <Row className="align-items-center">
                    <Col xs="8">
                      <h3 className="mb-0">Ajouter un Paymment</h3>
                    </Col>
                  </Row>
                </CardHeader>
                <CardBody>
                  <Form onSubmit={Register}>
                    <Row>
                      <Col lg="4">
                        <div className="form-group">
                          <label className="form-control-label" htmlFor="exampleFormControlSelect6">
                            Selectionner Tontine
                          </label>
                          <select name="id_daret" id="exampleFormControlSelect6" className="form-control" onChange={Valorform}>
                          {tontines.length > 0 && tontines.map((tontine)=>(
                            <option value={tontine.id}>{tontine.nom}</option>
                          ))}    
                          </select>
                        </div>
                      </Col>
                      <Col lg="4">
                        <div className="form-group">
                          <label className="form-control-label" htmlFor="exampleFormControlSelect6">
                            Selectionner Administracteur
                          </label>
                          <select id="exampleFormControlSelect6" className="form-control" onChange={Valorform} name="id_admin">
                          {admin.length > 0 && admin.map((a)=>(
                            <option value={a.id}>{a.nom}</option>
                          ))}                     
                          </select>
                        </div>
                      </Col>
                      <Col lg="4">
                        <div className="form-group">
                          <label className="form-control-label" htmlFor="exampleFormControlSelect6">
                            Selectionner Participant
                          </label>
                          <select id="exampleFormControlSelect6" className="form-control" onChange={Valorform} name="id_membre">
                          {utilisateurs.length > 0 && utilisateurs.map((user)=>(
                            <option value={user.id}>{user.nom}</option>
                          ))}                     
                          </select>
                        </div>
                      </Col>
                      <Col lg="4">
                        <FormGroup>
                          <label
                            className="form-control-label"
                            htmlFor="input-username"
                          >
                            Date
                          </label>
                          <Input
                            id="input-username"
                            placeholder="date"
                            type="date"
                            min={dataAtual}
                            name="date_payement"
                            onChange={Valorform}
                          />
                        </FormGroup>
                      </Col>
                    </Row>
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
{/*Lister tous les payments*/}
        <Row className="my-5">
          <Col className="mb-5 mb-xl-0" xl="12">
            <Card className="shadow">
              <CardHeader className="border-0">
                <Row className="align-items-center">
                  <div className="col">
                    <h3 className="mb-0">Liste des Paymments</h3>
                  </div>
                </Row>
              </CardHeader>
              <Table className="align-items-center table-flush" responsive>
                <thead className="thead-light">
                  <tr>
                    <th scope="col">Tontine</th>
                    <th scope="col">Participant</th>
                    <th scope="col">Date Paymment</th>
                  </tr>
                </thead>
                <tbody>
                {Payments.length > 0 && Payments.map((payement)=>{
                        // Formata a data de inscrição
                        const dateArray = payement.date_payement;
                        const formattedDate = 
                        dateArray && dateArray.length === 3
                            ? new Date(dateArray[0], dateArray[1] - 1, dateArray[2]).toLocaleDateString()
                            : 'Data de inscrição não disponível'; 
                        
                          return(
                            <tr>
                              <th scope="row">{payement.nom_daret}</th>
                              <td>{payement.nom_membre}</td>
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

export default Paymment;
