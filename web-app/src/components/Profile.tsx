import React from "react";
import { UserInfo } from "@/models";
import { useNavigate } from "react-router-dom";
import { useAuth } from "@/contexts/AuthContext";

interface Props {
  user: UserInfo;
}

const Profile: React.FC<Props> = ({ user }) => {
  const navigate = useNavigate();
  const { logout } = useAuth();

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-50">
      <div className="bg-white text-gray-500 max-w-md w-full mx-4 md:p-6 p-4 text-left text-sm rounded-xl shadow-[0px_0px_10px_0px] shadow-black/10">
        <div className="flex flex-col items-center">
          <img
            src={user.avatar}
            alt={`${user.firstName}'s avatar`}
            className="w-24 h-24 rounded-full shadow-md object-cover"
          />
          <h2 className="text-xl font-semibold mt-3 text-gray-800">
            {user.firstName} {user.lastName}
          </h2>
          {user.email && <p className="text-gray-500 text-sm">{user.email}</p>}
        </div>
        <button
          onClick={handleLogout}
          className="w-full mt-3 bg-indigo-500 py-2.5 rounded-full text-white cursor-pointer"
        >
          Logout
        </button>
      </div>
    </div>
  );
};

export default Profile;
