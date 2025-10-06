import { oauthConfig } from "@/configurations/oauthConfig";
import useAuthenticate from "@/hooks/useAuthenticate";
import { useState } from "react";

const Login = () => {
  const { mutateAsync: authenticate } = useAuthenticate();

  const handleOauthLogin = () => {
    const clientId = oauthConfig.clientId;
    const authUrl = oauthConfig.authUri;
    const callbackUrl = oauthConfig.redirectUri;

    const targetUrl = `${authUrl}?redirect_uri=${encodeURIComponent(
      callbackUrl
    )}&response_type=code&client_id=${clientId}&scope=openid%20email%20profile`;

    window.location.href = targetUrl;
  };

  const [payload, setPayload] = useState({
    username: "",
    password: "",
  });

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setPayload((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await authenticate(payload);
  };

  return (
    <div className="flex justify-center items-center h-screen bg-gray-100">
      <div className="bg-white text-gray-500 max-w-96 mx-4 md:p-6 p-4 text-left text-sm rounded-xl shadow-[0px_0px_10px_0px] shadow-black/10">
        <h2 className="text-2xl font-semibold mb-6 text-center text-gray-800">
          Welcome back
        </h2>
        <form onSubmit={handleSubmit}>
          <input
            id="username"
            className="w-full bg-transparent border my-3 border-gray-500/30 outline-none rounded-full py-2.5 px-4"
            type="text"
            autoComplete="username"
            placeholder="Enter your username"
            name="username"
            value={payload.username}
            onChange={handleInputChange}
            required
          />
          <input
            id="password"
            className="w-full bg-transparent border mt-1 border-gray-500/30 outline-none rounded-full py-2.5 px-4"
            type="password"
            autoComplete="current-password"
            placeholder="Enter your password"
            name="password"
            value={payload.password}
            onChange={handleInputChange}
            required
          />
          <div className="text-right py-4">
            <a className="text-blue-600 underline" href="#">
              Forgot Password
            </a>
          </div>
          <button
            type="submit"
            className="w-full mb-3 bg-indigo-500 py-2.5 rounded-full text-white cursor-pointer"
          >
            Log in
          </button>
        </form>
        <p className="text-center mt-4">
          Don’t have an account?{" "}
          <a href="#" className="text-blue-500 underline">
            Signup
          </a>
        </p>
        <button
          type="button"
          onClick={handleOauthLogin}
          className="w-full flex items-center gap-2 justify-center my-3 bg-white border border-gray-500/30 py-2.5 rounded-full text-gray-800 cursor-pointer"
        >
          <img
            className="h-4 w-4"
            src="https://raw.githubusercontent.com/prebuiltui/prebuiltui/main/assets/login/googleFavicon.png"
            alt="googleFavicon"
          />
          Log in with Google
        </button>
      </div>
    </div>
  );
};

export default Login;
