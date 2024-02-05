import User from "views/user/User.js";
import Detail from "views/user/Detail.js";
import Login from "views/examples/Login.js";

var Userroutes = [
    {
        path: "/tontines",
        name: "Tontines",
        icon: "ni ni-key-25 text-info",
        component: <User />,
        layout: "/user",
      },
      {
        path: "/detail",
        name: "Mes Tontines",
        icon: "ni ni-key-25 text-info",
        component: <Detail />,
        layout: "/user",
      },
      {
        path: "/login",
        name: "Login",
        icon: "ni ni-key-25 text-info",
        component: <Login />,
        layout: "/auth",
      },
];
export default Userroutes;