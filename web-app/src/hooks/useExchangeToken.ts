import { exchangeToken } from "@/apis/authApi";
import { useNavigate } from "react-router-dom";
import { useAuth } from "@/contexts/AuthContext";
import { useMutation } from "@tanstack/react-query";

const useExchangeToken = () => {
  const { login } = useAuth();
  const navigate = useNavigate();

  return useMutation({
    mutationFn: exchangeToken,
    onSuccess: (data) => {
      login(data.data!.accessToken);
      navigate("/", { replace: true });
    },
    onError: (error) => {
      console.error("Error exchanging token: ", error);
    },
  });
};

export default useExchangeToken;
