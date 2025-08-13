export interface RegisterRequest {
  username: string;
  password: string;
  role: string;
}


export interface LoginRequest {
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
}

