import Home from "@/pages/Home";
import Login from "@/pages/Login";
import NotFound from "@/pages/NotFound";
import Authenticate from "@/pages/Authenticate";
import { Navigate, Route, Routes } from "react-router-dom";
import CreatePassword from "@/pages/CreatePassword";
import { useAuth } from "@/contexts/AuthContext";

const AppRoutes = () => {
  const { token } = useAuth();
  return (
    <Routes>
      <Route
        path="/"
        element={token ? <Home /> : <Navigate to="/login" replace />}
      />
      <Route
        path="/login"
        element={!token ? <Login /> : <Navigate to="/" replace />}
      />
      <Route
        path="/create-password"
        element={token ? <CreatePassword /> : <Navigate to="/login" replace />}
      />
      <Route path="/authenticate" element={<Authenticate />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
};

export default AppRoutes;
