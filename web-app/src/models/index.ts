export type ApiResponse<T> = {
  code: number;
  message: string | undefined;
  data: T | undefined;
};

export type ExchangeTokenResponse = {
  accessToken: string;
  expiresIn: number;
  refreshToken: string;
  scope: string;
  tokenType: string;
};

export type UserDetails = {
  id: string;
  email: string;
  name: string;
  picture: string;
};

export type UserInfo = {
  id: string;
  email: string | undefined;
  firstName: string;
  lastName: string;
  avatar: string;
  hasPassword: boolean;
};

export type AuthenticationResponse = {
  accessToken: string;
};

export type AuthenticationPayload = {
  username: string;
  password: string;
};
