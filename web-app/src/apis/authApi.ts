import { axiosInstance } from "@/apis/client";
import {
  ApiResponse,
  ExchangeTokenResponse,
  AuthenticationPayload,
  AuthenticationResponse,
} from "@/models";

export const exchangeToken = async (code: string) => {
  const response = await axiosInstance.post<ApiResponse<ExchangeTokenResponse>>(
    `/auth/outbound/authentication?code=${code}`
  );

  return response.data;
};

export const authenticate = async (payload: AuthenticationPayload) => {
  const response = await axiosInstance.post<
    ApiResponse<AuthenticationResponse>
  >("/auth", payload);
  return response.data;
};
