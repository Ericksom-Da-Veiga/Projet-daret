import React from "react";

import Header from "components/Headers/Header.js";

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
                      <h3 className="mb-0">Ajouter un Versement</h3>
                    </Col>
                  </Row>
                </CardHeader>
                <CardBody>
                  <Form>
                    <Row>
                      <Col lg="4">
                        <div className="form-group">
                          <label className="form-control-label" htmlFor="exampleFormControlSelect6">
                            Selectionner Tontine
                          </label>
                          <select id="exampleFormControlSelect6" className="form-control">
                            <option>Tontine 1</option>
                            <option>Tontine 2</option>
                            <option>Tontine 3</option>
                          </select>
                        </div>
                      </Col>
                      <Col lg="4">
                        <div className="form-group">
                          <label className="form-control-label" htmlFor="exampleFormControlSelect6">
                            Selectionner Participant
                          </label>
                          <select id="exampleFormControlSelect6" className="form-control">
                            <option>Gestionnaire 1</option>
                            <option>Gestionnaire 2</option>
                            <option>Gestionnaire 3</option>
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
                          />
                        </FormGroup>
                      </Col>
                    </Row>
                    <Row>
                      <Col lg="4">
                        <Button color="info" type="button">
                          Ajouter
                        </Button>
                      </Col>
                    </Row>
                  </Form>
                </CardBody>
              </Card>
            </Col>
        </Row>

        <Row className="my-5">
          <Col className="mb-5 mb-xl-0" xl="12">
            <Card className="shadow">
              <CardHeader className="border-0">
                <Row className="align-items-center">
                  <div className="col">
                    <h3 className="mb-0">Liste des Versement</h3>
                  </div>
                </Row>
              </CardHeader>
              <Table className="align-items-center table-flush" responsive>
                <thead className="thead-light">
                  <tr>
                    <th scope="col">N</th>
                    <th scope="col">Tontine</th>
                    <th scope="col">Gestionnaire</th>
                    <th scope="col">Date</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>1</td>
                    <th scope="row">Tontine 1</th>
                    <td>Gestionnaire 1</td>
                    <td>03/04/2023</td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <th scope="row">Tontine 1</th>
                    <td>Gestionnaire 2</td>
                    <td>03/05/2023</td>
                  </tr>
                  <tr>
                    <td>3</td>
                    <th scope="row">Tontine 2</th>
                    <td>Gestionnaire 3</td>
                    <td>04/04/2023</td>
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

export default Paymment;
