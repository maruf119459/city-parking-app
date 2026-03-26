import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.my.app',
  appName: 'my-mobile',
  webDir: 'build',
  server: {
    hostname: 'localhost',
    androidScheme: 'http',
    allowNavigation: ['smart-parking-backend-u47b.onrender.com']
  }
};

export default config;