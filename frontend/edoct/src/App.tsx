import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Login from './login'
import AdminLogin from './admin_login'

const router = createBrowserRouter([
    {
        path: "/login",
        element: <Login />
    },
    {
        path: "/",
        element: <Login />
    },
    {
        path: "/admin",
        element: <AdminLogin />
    }
])

function App() {
    return (
        <RouterProvider router={router} />
    )
}

export default App