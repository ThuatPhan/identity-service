import React from "react";

interface Props {
  message?: string;
}

const Loading: React.FC<Props> = ({ message }) => {
  return (
    <div className="flex items-center justify-center h-screen bg-gray-100">
      <div className="flex items-center space-x-4">
        <div className="w-6 h-6 border-4 border-blue-300 border-t-transparent rounded-full animate-spin" />
        <span className="font-medium text-lg">
          {message ? message : "Loading..."}
        </span>
      </div>
    </div>
  );
};

export default Loading;
