import { useEffect } from "react";
import Loading from "@/components/Loading";
import useExchangeToken from "@/hooks/useExchangeToken";
import { useLocation, useNavigate } from "react-router-dom";

const Authenticate = () => {
  const { mutate: exchangeToken } = useExchangeToken();
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    const queryParams = new URLSearchParams(location.search);
    const code = queryParams.get("code");

    if (code) {
      exchangeToken(code);
    } else {
      navigate("/login", { replace: true });
    }
  }, []);

  return <Loading message="Authenticating..." />;
};

export default Authenticate;
