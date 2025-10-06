import Loading from "@/components/Loading";
import { useState } from "react";
import useUserInfo from "@/hooks/useUserInfo";
import useCreatePassword from "@/hooks/useCreatePassword";

const CreatePassword = () => {
  const { user, isPending } = useUserInfo();
  const {
    error,
    isPending: creating,
    mutateAsync: createPassword,
  } = useCreatePassword();
  const [password, setPassword] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await createPassword(password);
  };

  if (isPending || !user) {
    return <Loading />;
  }

  return (
    <div className="flex justify-center items-center h-screen bg-gray-100">
      <div className="bg-white text-gray-500 max-w-96 mx-4 md:p-6 p-4 text-left text-sm rounded-xl shadow-[0px_0px_10px_0px] shadow-black/10">
        <h2 className="text-2xl font-semibold mb-6 text-center text-gray-800">
          Create password
        </h2>
        <form onSubmit={handleSubmit}>
          <input
            id="username"
            name="username"
            autoComplete="username"
            className="w-full bg-gray-200 border my-3 border-gray-500/30 outline-none rounded-full py-2.5 px-4"
            disabled
            value={user.email}
          />
          <div className="w-full">
            <input
              id="password"
              className="w-full bg-transparent border mt-1 border-gray-500/30 outline-none rounded-full py-2.5 px-4"
              type="password"
              autoComplete="current-password"
              placeholder="Enter your password"
              name="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            <div className="py-4 text-red-500 text-sm text-left">
              <span>{error ? error.message : "\u00A0"}</span>
            </div>
          </div>

          <button
            type="submit"
            className="w-full mt-3 bg-indigo-500 py-2.5 rounded-full text-white cursor-pointer flex items-center justify-center gap-2 disabled:opacity-70 disabled:cursor-not-allowed"
            disabled={creating}
          >
            {creating && (
              <div className="w-4 h-4 border-2 border-white border-t-transparent rounded-full animate-spin" />
            )}
            {creating ? "Saving..." : "Save Password"}
          </button>
        </form>
        <p className="text-left mt-4">
          This password will be used to log in to your account with your
          username and password.
        </p>
      </div>
    </div>
  );
};

export default CreatePassword;
