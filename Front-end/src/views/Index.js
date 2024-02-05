
import { useState } from "react";

import classnames from "classnames";

import Chart from "chart.js";

import { Line, Bar } from "react-chartjs-2";

import {
  Button,
  Card,
  CardHeader,
  CardBody,
  NavItem,
  NavLink,
  Nav,
  Progress,
  Table,
  Container,
  Row,
  Col,
} from "reactstrap";

import {
  chartOptions,
  parseOptions,
  chartExample1,
  chartExample2,
} from "variables/charts.js";

import Header from "components/Headers/Header.js";

const Index = (props) => {
  const [activeNav, setActiveNav] = useState(1);
  const [chartExample1Data, setChartExample1Data] = useState("data1");

  if (window.Chart) {
    parseOptions(Chart, chartOptions());
  }

  const toggleNavs = (e, index) => {
    e.preventDefault();
    setActiveNav(index);
    setChartExample1Data("data" + index);
  };
  return (
    <>
      <Header />
      {/* Page content */}
      <Container className="mt--7" fluid>
        <Row className="mt-5">
          <Col className="mb-5 mb-xl-0" xl="12">
            <Card className="shadow">
              <CardHeader className="border-0">
                <Row className="align-items-center">
                  <div className="col">
                    <h3 className="mb-0">Dernier Membres</h3>
                  </div>
                </Row>
              </CardHeader>


              {/* tabelas dos membros*/}
              <Table className="align-items-center table-flush" responsive>
                <thead className="thead-light">
                  <tr>
                    <th scope="col">N</th>
                    <th scope="col">Nom et Prenoms</th>
                    <th scope="col">Contact</th>
                    <th scope="col">Email</th>
                    <th scope="col">Nombre Tontine</th>
                    <th scope="col">Statut</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>1</td>
                    <th scope="row">John 1</th>
                    <td>0600000001</td>
                    <td>john1@gmail.com</td>
                    <td>2</td>
                    <td>
                      <i className="ni ni-check-bold text-success mr-3" /> Actif
                    </td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <th scope="row">John 2</th>
                    <td>0600000002</td>
                    <td>john2@gmail.com</td>
                    <td>3</td>
                    <td>                      
                      <i className="ni ni-check-bold text-success mr-3" /> Actif
                    </td>
                  </tr>
                  <tr>
                    <td>3</td>
                    <th scope="row">John 3</th>
                    <td>0600000003</td>
                    <td>john3@gmail.com</td>
                    <td>2</td>
                    <td>
                      <i className="ni ni-check-bold text-success mr-3" /> Actif
                    </td>
                  </tr>
                  <tr>
                    <td>4</td>
                    <th scope="row">John 4</th>
                    <td>0600000004</td>
                    <td>john4@gmail.com</td>
                    <td>3</td>
                    <td>
                      <i className="ni ni-check-bold text-success mr-3" /> Actif
                    </td>
                  </tr>
                  <tr>
                    <td>5</td>
                    <th scope="row">John 5</th>
                    <td>0600000005</td>
                    <td>john5@gmail.com</td>
                    <td>2</td>
                    <td>
                      <i className="ni ni-check-bold text-success mr-3" /> Actif
                    </td>
                  </tr>
                </tbody>
              </Table>
              {/* final da lista de membros*/}
              
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default Index;
