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


  const User = () => {
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
                    <h3 className="mb-0">Mes Tontines</h3>
                  </div>
                </Row>
              </CardHeader>
              <Table className="align-items-center table-flush" responsive>
                <thead className="thead-light">
                  <tr>
                    <th scope="col">N</th>
                    <th scope="col">Name</th>
                    <th scope="col">Nb Membre</th>
                    <th scope="col">Montant Max</th>
                    <th scope="col">Montant/Personne</th>
                    <th scope="col">Inscription</th>
                    <th scope="col">Creation</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>1</td>
                    <th scope="row">Tontine 1</th>
                    <td>4/10</td>
                    <td>16 000</td>
                    <td>1 000</td>
                    <td>
                      <i className="ni ni-check-bold text-success mr-3" /> Ouvert
                    </td>
                    <td>01/04/2024</td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <th scope="row">Tontine 2</th>
                    <td>7/10</td>
                    <td>7 000</td>
                    <td>500</td>
                    <td>                      
                      <i className="ni ni-check-bold text-success mr-3" /> Ouvert
                    </td>
                    <td>03/02/2024</td>
                  </tr>
                  <tr>
                    <td>3</td>
                    <th scope="row">Tontine 3</th>
                    <td>5/10</td>
                    <td>1 000</td>
                    <td>8 500</td>
                    <td>
                      <i className="ni ni-fat-remove text-warning mr-3" />Fermé
                    </td>
                    <td>12/05/2024</td>
                  </tr>
                  <tr>
                    <td>4</td>
                    <th scope="row">Tontine 4</th>
                    <td>9/10</td>
                    <td>9 000</td>
                    <td>200</td>
                    <td>
                      <i className="ni ni-fat-remove text-warning mr-3" />Fermé
                    </td>
                    <td>22/06/2024</td>
                  </tr>
                  <tr>
                    <td>5</td>
                    <th scope="row">Tontine 5</th>
                    <td>0/10</td>
                    <td>12 000</td>
                    <td>500</td>
                    <td>
                      <i className="ni ni-check-bold text-success mr-3" /> Ouvert
                    </td>
                    <td>10/04/2024</td>
                  </tr>
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
  
  export default User;
  