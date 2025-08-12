import React from 'react';
import { Link } from 'react-router-dom';
import { Building2, ArrowRight } from 'lucide-react';

const Homepage = () => {
  return (
    <div className="min-h-[calc(100vh-5rem)] bg-gradient-to-br from-slate-50 via-blue-50 to-indigo-50 flex items-center justify-center">
      {/* Simple Hero Section */}
      <section className="container mx-auto px-6 py-12">
        <div className="text-center max-w-4xl mx-auto">
          
          {/* Logo */}
          <div className="flex items-center justify-center mb-8">
            <div className="w-16 h-16 bg-gradient-to-br from-blue-600 to-blue-700 rounded-2xl flex items-center justify-center shadow-lg">
              <Building2 className="h-8 w-8 text-white" />
            </div>
          </div>
          
          {/* Hero Text */}
          <h1 className="text-4xl md:text-5xl lg:text-6xl font-bold text-gray-900 mb-6 leading-tight">
            Property Management
            <span className="block text-blue-600 mt-2">Made Simple</span>
          </h1>
          
          <p className="text-lg md:text-xl text-gray-600 mb-8 leading-relaxed max-w-2xl mx-auto">
            Manage your properties, track revenue, and grow your business with our modern platform.
          </p>
          
          {/* Single CTA Button */}
          <div className="flex justify-center">
            <Link
              to="/dashboard"
              className="btn btn-primary text-base md:text-lg px-6 md:px-8 py-3 md:py-4 inline-flex items-center gap-3 shadow-lg hover:shadow-xl transition-all duration-300 rounded-lg"
            >
              Get Started
              <ArrowRight className="h-5 w-5" />
            </Link>
          </div>
          
        </div>
      </section>
    </div>
  );
};

export default Homepage;