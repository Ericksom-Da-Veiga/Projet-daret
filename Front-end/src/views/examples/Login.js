import axios from "axios";
import { Navigate } from 'react-router-dom';
import {useState,useEffect} from 'react';
import {
  Button,
  Card,
  CardHeader,
  CardBody,
  FormGroup,
  Form,
  Input,
  InputGroupAddon,
  InputGroupText,
  InputGroup,
  Row,
  Col,
} from "reactstrap";

const Login = () => {

  const [Error, setError] = useState("");

  const updateform = e => setUserLogin({...userLogin, [e.target.name]: e.target.value});
  const [isLoggedIn, setLoggedIn] = useState(false);

  const [userLogin, setUserLogin] = useState({
    mail:'',
    mot_passe:'',
  })
  const handleLogin = async (e) => {
    e.preventDefault();

    console.log(userLogin)
    const headers = {
      'headers': {
        'Content-Type': 'application/json'
      }
    }

    try {
      const response = await axios.post('http://localhost:8080/login', userLogin, headers);

      console.log(response)

      // Se a solicitação for bem-sucedida, o token JWT estará em response.data.token
      const token = response.data.token;

      // Armazenar o token no localStorage ou em um estado de autenticação global
      localStorage.setItem('jwtToken', token);

      // Lidar com o sucesso do login, redirecionar ou atualizar o estado do componente, etc.
      console.log('Login bem-sucedido. Token JWT:', token);
      setLoggedIn(true);  // Ativar o redirecionamento

    } catch (err) {
      // Lidar com erros de login
      setError('Probleme dans le login. Voulez verifier vos informations.');
      console.error('Erro durante o login:', err);
    }
  };

  if (isLoggedIn) {
    return <Navigate to="/admin/index" />;
  }

  return (
    <>
      <Col lg="5" md="7">
        <Card className="bg-secondary shadow border-0">
         
            
          <CardBody className="px-lg-5 py-lg-5">
            <div className="text-center text-muted mb-4">
              <h1>Login</h1>
              {Error? <h3>{Error}</h3>:""}
            </div>
            <Form role="form" onSubmit={handleLogin}>
              <FormGroup className="mb-3">
                <InputGroup className="input-group-alternative">
                  <InputGroupAddon addonType="prepend">
                    <InputGroupText>
                      <i className="ni ni-email-83" />
                    </InputGroupText>
                  </InputGroupAddon>
                  <Input
                    placeholder="Email"
                    type="email"
                    required
                    autoComplete="new-email"
                    name="mail"
                    onChange={updateform}
                  />
                </InputGroup>
              </FormGroup>
              <FormGroup>
                <InputGroup className="input-group-alternative">
                  <InputGroupAddon addonType="prepend">
                    <InputGroupText>
                      <i className="ni ni-lock-circle-open" />
                    </InputGroupText>
                  </InputGroupAddon>
                  <Input
                    placeholder="Password"
                    type="password"
                    autoComplete="new-password"
                    name="mot_passe"
                    onChange={updateform}
                    required
                  />
                </InputGroup>
              </FormGroup>
              <div className="text-center">
                <Button className="my-4" color="primary" type="submit">
                  Login
                </Button>
              </div>
            </Form>
          </CardBody>
        </Card>
      </Col>
    </>
  );
};

export default Login;
