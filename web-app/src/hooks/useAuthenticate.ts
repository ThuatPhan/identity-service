import toast from "react-hot-toast";
import { authenticate } from "@/apis/authApi";
import { useAuth } from "@/contexts/AuthContext";
import { useMutation } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";

const useAuthenticate = () => {
  const { login } = useAuth();
  const navigate = useNavigate();

  return useMutation({
    mutationFn: authenticate,
    onSuccess: (data) => {
      login(data.data!.accessToken);
      navigate("/", { replace: true });
    },
    onError: (error) => toast.error(error.message),
  });
};

export default useAuthenticate;
