export * from './auth.service';
import { AuthService } from './auth.service';
export * from './game.service';
import { GameService } from './game.service';
export * from './lostInTranslation.service';
import { LostInTranslationService } from './lostInTranslation.service';
export const APIS = [AuthService, GameService, LostInTranslationService];
