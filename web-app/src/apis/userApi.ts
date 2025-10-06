import { axiosInstance } from "./client";
import { ApiResponse, UserInfo } from "@/models";

export const getUserInfo = async () => {
  const response = await axiosInstance.get<ApiResponse<UserInfo>>(
    "/users/info"
  );
  return response.data.data;
};

export const createPassword = async (password: string) => {
  return axiosInstance.post("/users/create-password", { password });
};
