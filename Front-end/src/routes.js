
import Index from "views/Index.js";
import Gestionnaire from "views/examples/Gestionnaire.js";
import Tontine from "views/examples/Tontine.js";
import Login from "views/examples/Login.js";
import Paymment from "views/examples/Paymment.js";
import Versement from "views/examples/Versement.js";


var routes = [
  {
    path: "/index",
    name: "Dashboard",
    icon: "ni ni-tv-2 text-info",
    component: <Index />,
    layout: "/admin",
  },
  {
    path: "/tontine",
    name: "Tontine",
    icon: "ni ni-collection text-info",
    component: <Tontine />,
    layout: "/admin",
  },
  {
    path: "/gestionnaire",
    name: "Utilisateur",
    icon: "ni ni-single-02 text-info",
    component: <Gestionnaire />,
    layout: "/admin",
  },
  {
    path: "/paymment",
    name: "Paimment",
    icon: "ni ni-credit-card text-info",
    component: <Paymment />,
    layout: "/admin",
  },
  {
    path: "/versement",
    name: "Versement",
    icon: "ni ni-credit-card text-info",
    component: <Versement />,
    layout: "/admin",
  },
  {
    path: "/login",
    name: "Login",
    icon: "ni ni-key-25 text-info",
    component: <Login />,
    layout: "/auth",
  },
];
export default routes;
