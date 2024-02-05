import { Link } from "react-router-dom";

import {
  DropdownMenu,
  DropdownItem,
  UncontrolledDropdown,
  DropdownToggle,
  Form,
  FormGroup,
  InputGroupAddon,
  InputGroupText,
  Input,
  InputGroup,
  Navbar,
  Nav,
  Container,
  Media,
  Row,
  Col,
  CardHeader,
  Button,
  Table,
  CardBody,
  Card,
} from "reactstrap";


  const Detail = () => {
    return (
      <>
        
        
      <Navbar className="navbar-top navbar-dark mt-8" style={{position: 'relative'}} expand="md" id="navbar-main">
        <Container fluid>
          <Link
            className="h4 mb-0 text-white text-uppercase d-none d-lg-inline-block"
            to="/"
          >Tontine
          </Link>
          <Form className="navbar-search navbar-search-dark form-inline d-none d-md-flex ml-lg-auto">
            <FormGroup className="mb-0">
              <InputGroup className="input-group-alternative">
                <InputGroupAddon addonType="prepend">
                  <InputGroupText>
                    <i className="fas fa-search" />
                  </InputGroupText>
                </InputGroupAddon>
                <Input placeholder="Search" type="text" />
              </InputGroup>
            </FormGroup>
          </Form>
        </Container>
      </Navbar>

      <Container fluid>

        

        <Row className="my-5">
          <Col className="mb-5 mb-xl-0" xl="12">
            <Card className="shadow">
              <CardHeader className="border-0">
                <Row className="align-items-center">
                  <div className="col">
                    <h3 className="mb-0">Dernier Paymments</h3>
                  </div>
                </Row>
              </CardHeader>
              <Table className="align-items-center table-flush" responsive>
                <thead className="thead-light">
                  <tr>
                    <th scope="col">Tour</th>
                    <th scope="col">Payé</th>
                    <th scope="col">Membre</th>
                    <th scope="col">Cotisation</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>1</td>
                    <th scope="row">                      
                        <i className="ni ni-check-bold text-success mr-3" /> Oui
                    </th>
                    <td>Martin 1</td>
                    <td>0</td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <th scope="row">                      
                        <i className="ni ni-check-bold text-success mr-3" /> Oui
                    </th>
                    <td>Martin 2</td>
                    <td>0</td>
                  </tr>
                  <tr>
                    <td>3</td>
                    <th scope="row">                      
                        <i className="ni ni-fat-remove text-danger mr-3" /> Non
                    </th>
                    <td>Martin 3</td>
                    <td>0</td>
                  </tr>
                </tbody>
              </Table>
            </Card>
          </Col>
        </Row>

        <Row className="my-5">
          <Col className="mb-5 mb-xl-0" xl="12">
            <Card className="shadow">
              <CardHeader className="border-0">
                <Row className="align-items-center">
                  <div className="col">
                    <h3 className="mb-0">Memebre</h3>
                  </div>
                </Row>
              </CardHeader>
              <Table className="align-items-center table-flush" responsive>
                <thead className="thead-light">
                  <tr>
                  <th scope="col">N</th>
                  <th scope="col">Nom</th>
                    <th scope="col">Prenom</th>
                    <th scope="col">Sexe</th>
                    <th scope="col">Telephone</th>
                    <th scope="col">Adresse e-mail</th>
                  </tr>
                </thead>
                <tbody>
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
  
  export default Detail;
  