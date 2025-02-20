import { createClient, SupabaseClient } from '@supabase/supabase-js';
import type {Database} from '@/database.types'

const supabaseUrl = 'https://jntimjrhbndwwrxwpkcg.supabase.co';
const supabaseAnonKey =
  'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpudGltanJoYm5kd3dyeHdwa2NnIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjA2NjI1OTQsImV4cCI6MjAzNjIzODU5NH0.ujmrWqNTVK8GBbfFGssTshLOL5uLu5ww2rrzzapoaM4';

export const supabase = createClient<SupabaseClient<Database>>(supabaseUrl, supabaseAnonKey);
