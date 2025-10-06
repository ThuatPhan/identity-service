import Loading from "@/components/Loading";
import Profile from "@/components/Profile";
import useUserInfo from "@/hooks/useUserInfo";
import { Navigate } from "react-router-dom";

const Home = () => {
  const { user, isPending } = useUserInfo();

  if (isPending || !user) {
    return <Loading message="Loading user info..." />;
  }

  if (!user.hasPassword) {
    return <Navigate to="/create-password" />;
  }

  return <Profile user={user} />;
};

export default Home;
