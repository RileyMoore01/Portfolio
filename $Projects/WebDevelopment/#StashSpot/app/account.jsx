import Account from '@/components/Account';
import { View } from 'react-native';
import {useSession} from '@/context/session.context'

export default function AccountScreen() {
  const session = useSession();
  return <View><Account session={session} /></View>;
}
