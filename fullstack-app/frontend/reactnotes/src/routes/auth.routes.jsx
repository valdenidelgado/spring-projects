import {Route, Routes} from 'react-router-dom';
import {SignIn} from "../pages/SignIn/index.jsx";
import {SignUp} from "../pages/SignUp/index.jsx";

export function AuthRoutes() {
  return (
    <Routes>
      <Route path="/" element={<SignIn />} />
      <Route path="/register" element={<SignUp />} />
    </Routes>
  )
}