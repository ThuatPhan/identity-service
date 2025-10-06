import toast from "react-hot-toast";
import { useNavigate } from "react-router-dom";
import { createPassword } from "@/apis/userApi";
import { useMutation, useQueryClient } from "@tanstack/react-query";

const useCreatePassword = () => {
  const navigate = useNavigate();
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: createPassword,
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ["userinfo"] });
      navigate("/");
      toast.success("Password has been created successful");
    },
  });
};

export default useCreatePassword;
