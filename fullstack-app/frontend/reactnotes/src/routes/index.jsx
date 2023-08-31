import {BrowserRouter} from "react-router-dom";
import {AppRoutes} from "./app.routes.jsx";

export function Routes() {
  return (
    <BrowserRouter>
      <AppRoutes />
    </BrowserRouter>
  )
}