// src/api.js
export const login = async (username, password) => {
    // Fake API call (có thể thay bằng fetch/axios)
    if (username === "admin" && password === "123456") {
      return { success: true, token: "fake-jwt-token" };
    }
    throw new Error("Invalid credentials");
  };
  