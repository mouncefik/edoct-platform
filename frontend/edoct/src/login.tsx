import { login } from "./services/api.ts";
import { FormEvent, useState } from "react";

export default function Login() {
    const [credentials, setCredentials] = useState({ cin: '', password: '' });

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        try {
            const response = await login(credentials);
            console.log(response);
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="min-h-screen bg-gray-100 flex items-center justify-center">
            <div className="bg-white p-8 rounded-lg shadow-md w-96">
                <div className="text-center mb-6">
                    <h2 className="text-2xl font-bold text-gray-800">E-Doctorant</h2>
                    <p className="text-gray-600 mt-2">Connectez-vous à votre compte</p>
                </div>

                <form onSubmit={handleSubmit} className="space-y-4">
                    <div>
                        <label htmlFor="cin" className="block text-sm font-medium text-gray-700">CIN</label>
                        <input
                            id="cin"
                            type="text"
                            value={credentials.cin}
                            onChange={e => setCredentials({...credentials, cin: e.target.value})}
                            className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 focus:border-blue-500 focus:outline-none focus:ring-1 focus:ring-blue-500"
                        />
                    </div>

                    <div>
                        <label htmlFor="password" className="block text-sm font-medium text-gray-700">Mot de passe</label>
                        <input
                            id="password"
                            type="password"
                            value={credentials.password}
                            onChange={e => setCredentials({...credentials, password: e.target.value})}
                            className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 focus:border-blue-500 focus:outline-none focus:ring-1 focus:ring-blue-500"
                        />
                    </div>

                    <button
                        type="submit"
                        className="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
                    >
                        Se connecter
                    </button>
                </form>
            </div>
        </div>
    );
}