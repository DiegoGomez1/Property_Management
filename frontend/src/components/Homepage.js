import React from 'react';
import { Link } from 'react-router-dom';
import { ArrowRight } from 'lucide-react';

const Homepage = () => {
  return (
    <section className="bg-gradient-to-br from-slate-50 via-blue-50 to-indigo-50 min-h-screen flex items-center">
      <div className="container mx-auto px-6 lg:px-20 grid grid-cols-1 lg:grid-cols-2 gap-12 items-center pt-12">
        
        {/* Left: Text Content */}
        <div className="space-y-6">
          <h1 className="text-4xl md:text-5xl lg:text-6xl font-bold text-gray-900 leading-tight">
            Property Management
            <span className="block text-blue-600 mt-2">Made Simple</span>
          </h1>
          
          <p className="text-lg md:text-xl text-gray-600 max-w-lg leading-relaxed">
            Manage your properties, track revenue, and grow your business with our modern platform — 
            designed for speed, simplicity, and success.
          </p>

          <div>
            <Link
              to="/dashboard"
              className="inline-flex items-center gap-3 bg-blue-600 hover:bg-blue-700 text-white font-semibold text-base px-6 py-3 rounded-lg shadow-lg hover:shadow-xl transition-all duration-300"
            >
              Get Started
              <ArrowRight className="h-4 w-4" />
            </Link>
          </div>
        </div>

        {/* Right: Illustration / Placeholder */}
        <div className="flex justify-center">
          <div className="w-full max-w-md aspect-square bg-white rounded-2xl shadow-lg border border-slate-100 flex items-center justify-center text-gray-400 text-lg">
            Illustration / Graphic
          </div>
        </div>

      </div>
    </section>
  );
};

export default Homepage;
