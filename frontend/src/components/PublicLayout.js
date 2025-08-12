import React from 'react';
import { Link } from 'react-router-dom';
import { Building2, Menu, X } from 'lucide-react';

const PublicLayout = ({ children }) => {
  const [mobileMenuOpen, setMobileMenuOpen] = React.useState(false);

  return (
    <div className="min-h-screen">
      {/* Navigation Header */}
      <nav className="fixed w-full top-0 z-50 bg-white/95 backdrop-blur-md border-b border-gray-200/50">
        <div className="container mx-auto px-6">
          <div className="flex items-center justify-between h-20">
            {/* Logo */}
            <Link to="/" className="flex items-center space-x-3 hover:opacity-80 transition-opacity">
              <div className="w-10 h-10 bg-gradient-to-br from-blue-600 to-indigo-600 rounded-lg flex items-center justify-center">
                <Building2 className="h-6 w-6 text-white" />
              </div>
              <span className="text-xl font-bold text-gray-900">PropManage</span>
            </Link>

            {/* Desktop Navigation */}
            <nav className="hidden lg:flex items-center space-x-12">
              <Link to="/" className="text-gray-700 hover:text-blue-600 font-medium text-base transition-colors px-2">
                Home
              </Link>
              <Link to="/dashboard" className="text-gray-700 hover:text-blue-600 font-medium text-base transition-colors px-2">
                Dashboard
              </Link>
            </nav>

            {/* Desktop CTA Buttons */}
            <div className="hidden md:flex items-center space-x-6">
              <Link 
                to="/login" 
                className="text-gray-700 hover:text-blue-600 font-medium transition-colors text-lg px-4 py-2"
              >
                Sign In
              </Link>
              <Link 
                to="/dashboard" 
                className="bg-blue-600 hover:bg-blue-700 text-white font-semibold px-6 py-3 rounded-lg shadow-md hover:shadow-lg transition-all duration-300 text-lg"
              >
                Get Started
              </Link>
            </div>

            {/* Mobile menu button */}
            <button
              className="lg:hidden p-3 text-gray-700 hover:text-blue-600 transition-colors rounded-lg hover:bg-gray-100"
              onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
            >
              {mobileMenuOpen ? <X className="h-7 w-7" /> : <Menu className="h-7 w-7" />}
            </button>
          </div>

          {/* Mobile Navigation */}
          {mobileMenuOpen && (
            <div className="lg:hidden py-6 border-t border-gray-200">
              <div className="flex flex-col space-y-6">
                <Link 
                  to="/" 
                  className="text-gray-700 hover:text-blue-600 font-medium text-base py-2"
                  onClick={() => setMobileMenuOpen(false)}
                >
                  Home
                </Link>
                <Link 
                  to="/dashboard" 
                  className="text-gray-700 hover:text-blue-600 font-medium text-base py-2"
                  onClick={() => setMobileMenuOpen(false)}
                >
                  Dashboard
                </Link>
                <div className="border-t border-gray-200 pt-6 flex flex-col space-y-4">
                  <Link 
                    to="/login" 
                    className="text-gray-700 hover:text-blue-600 font-medium transition-colors text-lg py-2"
                    onClick={() => setMobileMenuOpen(false)}
                  >
                    Sign In
                  </Link>
                  <Link 
                    to="/dashboard" 
                    className="bg-blue-600 hover:bg-blue-700 text-white font-semibold px-6 py-3 rounded-lg shadow-md hover:shadow-lg transition-all duration-300 text-lg text-center"
                    onClick={() => setMobileMenuOpen(false)}
                  >
                    Get Started
                  </Link>
                </div>
              </div>
            </div>
          )}
        </div>
      </nav>

      {/* Main Content */}
      <main className="pt-20">
        {children}
      </main>

    </div>
  );
};

export default PublicLayout;