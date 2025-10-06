import { useQuery } from "@tanstack/react-query";
import { getUserInfo } from "@/apis/userApi";

const useUserInfo = () => {
  const query = useQuery({
    queryFn: getUserInfo,
    queryKey: ["userinfo"],
  });
  return { ...query, user: query.data };
};

export default useUserInfo;
