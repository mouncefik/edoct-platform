import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom'; // Replace location.href

const AdminLogin = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError('');
        if (!email || !password) {
            setError('Email and password are required.');
            return;
        }

        try {
            const response = await fetch('/api/auth/admin/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }),
            });

            const contentType = response.headers.get('content-type');
            let data;

            if (contentType && contentType.includes('application/json')) {
                data = await response.json();
            } else {
                throw new Error('Unexpected response format');
            }

            if (!response.ok) {
                throw new Error(data?.message || 'Login failed');
            }

            if (!data.email || !data.role || !data.token) {
                throw new Error('Incomplete response from server');
            }

            localStorage.setItem('adminData', JSON.stringify(data));
            navigate('/dashboard');
        } catch (err) {
            console.error('Error during login:', err);
            const errorMessage =
                err instanceof Error ? err.message : 'Unexpected error. Please try again.';
            setError(errorMessage);
        }
    };

    return (
        <div className="min-h-screen bg-gray-100 flex flex-col justify-center items-center">
            <div className="bg-white p-8 rounded-lg shadow-md w-96">
                <h1 className="text-2xl font-bold text-center mb-6 text-gray-600">Administrator Login</h1>

                {error && (
                    <div
                        className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4"
                        role="alert"
                        aria-live="assertive"
                    >
                        {error}
                    </div>
                )}

                <form onSubmit={handleSubmit} className="space-y-4">
                    <div>
                        <label htmlFor="email" className="block text-sm font-medium text-gray-700">
                            Email
                        </label>
                        <input
                            type="email"
                            id="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 focus:border-red-500 focus:outline-none focus:ring-1 focus:ring-red-500"
                            required
                        />
                    </div>

                    <div>
                        <label htmlFor="password" className="block text-sm font-medium text-gray-700">
                            Password
                        </label>
                        <input
                            type="password"
                            id="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 focus:border-red-500 focus:outline-none focus:ring-1 focus:ring-red-500"
                            required
                        />
                    </div>

                    <button
                        type="submit"
                        className="w-full bg-red-600 text-white py-2 px-4 rounded-md hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2"
                    >
                        Sign In
                    </button>
                </form>
            </div>
        </div>
    );
};

export default AdminLogin;
