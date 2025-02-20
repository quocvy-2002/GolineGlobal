import axios from "axios";

const API_BASE_URL = "http://localhost:8080";

export const login = (credentials) => axios.post(`${API_BASE_URL}/lessor/login`, credentials);

export const listProduct = async (page = 0, size = 10, keyword = '', sort = []) => {
  try {
    const params = { page, size };
    
    if (keyword) {
      params.keyword = keyword;
    }
    
    if (sort.length > 0) {
      params.sort = sort;
    }

    const response = await axios.get(`${API_BASE_URL}/products`, { params });
    return response.data;
  } catch (error) {
    console.error('Lỗi khi lấy danh sách sản phẩm:', error);
    throw error;
  }
};

export const createProduct = (product) => axios.post(`${API_BASE_URL}/products`, product);
