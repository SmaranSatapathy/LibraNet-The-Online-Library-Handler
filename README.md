# 📚 LibraNet – Online Library Handler

LibraNet is a simple console-based **library management system** built in Java.  
It manages **Books, Audiobooks, and E-Magazines** with support for:

- ✅ Borrowing & Returning Items (with late fine calculation)
- ✅ Checking Availability Status
- ✅ Playing Audiobooks (via `Playable` interface)
- ✅ Archiving E-Magazines
- ✅ Displaying Item Details (Title, Author, Status)

### 🔧 Features
- **Books:** Show page count.
- **Audiobooks:** Play duration and `play()` functionality.
- **E-Magazines:** Archive issues by number.
- **Fine Calculation:** `₹10/day` fine if returned after 14 days (simple string-based calculation).
- **Robust Input Handling:** Prevents program crash on invalid inputs.
