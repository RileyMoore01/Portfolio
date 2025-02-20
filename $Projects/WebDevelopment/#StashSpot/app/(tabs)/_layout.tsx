import React from 'react';
import { Image } from 'react-native';
import { Tabs } from 'expo-router';

// Box images
import boxIcon from '@/assets/images/box.png';
import boxIconOutline from '@/assets/images/box-outline.png';

// Search images
import searchIcon from '@/assets/images/search-icon.png';
import searchIconOutline from '@/assets/images/search-icon-outlined.png';

export default function TabsLayout() {
  return (
    <Tabs
      screenOptions={({ route }) => ({
        headerShown: false,
        tabBarIcon: ({ color, focused }) => {
          let iconSource;

          if (route.name === '(boxes)') {
            iconSource = focused ? boxIconOutline : boxIcon;
          } else {
            iconSource = focused ? searchIconOutline : searchIcon;
          }

          return (
            <Image
              source={iconSource}
              style={{ width: 24, height: 24, tintColor: color }}
            />
          );
        },
      })}
    >
      <Tabs.Screen
        name="(boxes)"
        options={{
          title: 'Boxes',
        }}
      />
      <Tabs.Screen
        name="search"
        options={{
          title: 'Search',
        }}
      />
    </Tabs>
  );
}

// import React from 'react';
// import { Image } from 'react-native';
// import { Tabs } from 'expo-router';

// // Box images
// import boxIcon from '@/assets/images/box.png';
// import boxIconOutline from '@/assets/images/box-outline.png';

// // Search images
// import searchIcon from '@/assets/images/search-icon.png';
// import searchIconOutline from '@/assets/images/search-icon-outlined.png';

// export default function TabsLayout() {
//   return (
//     <Tabs screenOptions={{ headerShown: false }}>
//       <Tabs.Screen
//         name="(boxes)"
//         options={{
//           title: 'Boxes',
//           tabBarIcon: ({ color, focused }) => {
//             console.log('Boxes focused:', focused);
//             return (
//               <Image
//                 source={focused ? boxIconOutline : boxIcon}
//                 style={{ width: 24, height: 24, tintColor: color }}
//               />
//             );
//           },
//         }}
//       />
//       <Tabs.Screen
//         name="search"
//         options={{
//           title: 'Search',
//           tabBarIcon: ({ color, focused }) => {
//             console.log('Search focused:', focused);
//             return (
//               <Image
//                 source={focused ? searchIconOutline : searchIcon}
//                 style={{ width: 24, height: 24, tintColor: color }}
//               />
//             );
//           },
//         }}
//       />
//     </Tabs>
//   );
// }