const API_URL = 'http://localhost:8080/api'

export const login = async (credentials: { cin: string; password: string }) => {
    const response = await fetch(`${API_URL}/auth/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(credentials)
    })
    return response.json()
}