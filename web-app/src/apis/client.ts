import { envConfig } from "@/configurations/envConfig";
import { getToken } from "@/helpers/localStorage";
import axios from "axios";

const privateRoutes = ["/users"];

export const axiosInstance = axios.create({
  baseURL: envConfig.baseApiUrl,
});

axiosInstance.interceptors.request.use(
  (config) => {
    // Add authorization header for private routes
    const isPrivate = privateRoutes.some((route) =>
      config.url?.startsWith(route)
    );

    const token = getToken();

    if (isPrivate && token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }

    return config;
  },
  (error) => Promise.reject(error)
);

axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => {
    const response = error?.response;
    if (response) {
      // Retrieve error code and message from server response
      const { code, message } = response.data;
      return Promise.reject({ code, message });
    }
    return Promise.reject(error);
  }
);
