package test.locale;

public enum Keyword {

    // ── Button ────────────────────────────────────────────────────────────────────
    LANGUAGE                ("English",                          "Tiếng Việt",                        "English",                           "Tiếng Việt"),
    LANG_PICKER_VN          ("Vietnamese",                       "Tiếng Việt",                        "Vietnamese",                        "Tiếng Việt"),
    LANG_PICKER_EN          ("English",                          "Tiếng Anh",                         "English",                           "Tiếng Anh"),
    CONTINUE                ("Continue",                         "Tiếp tục",                          "Continue",                          "Tiếp tục"),
    NEXT                    ("Next",                             "Tiếp theo",                         "Next",                              "Tiếp theo"),
    CLOSE                   ("Close",                            "Đóng",                              "Close",                             "Đóng"),
    CONFIRM                 ("Confirm",                          "Xác nhận",                          "Confirm",                           "Xác nhận"),
    DISMISS                 ("Dismiss",                          "Dismiss",                           "Dismiss",                           "Dismiss"),
    AGREE                   ("Agree",                            "Đồng ý",                            "Agree",                             "Đồng ý"),
    BACK                    ("Back",                             "Quay lại",                          "Back",                              "Quay lại"),
    DONE                    ("Done",                             "Xong",                              "Done",                              "Xong"),
    SAVE                    ("Save",                             "Lưu",                               "Save",                              "Lưu"),
    LOG_IN                  ("Log in",                           "Đăng nhập",                         "Login",                             "Đăng nhập"),
    SIGN_OUT                ("Sign out",                         "Đăng xuất",                         "Sign out",                          "Đăng xuất"),
    LATER                   ("Later",                            "Để sau",                            "Later",                             "Để sau"),
    REGISTER_LATER          ("Register later",                   "Đăng ký sau",                       "Register later",                    "Đăng ký sau"),
    UNDERSTAND              ("I understand",                     "Tôi đã hiểu",                       "I understand",                      "Tôi đã hiểu"),
    UPDATE                  ("Update",                           "Cập nhật",                          "Update",                            "Cập nhật"),
    DELETE                  ("Delete",                           "Xóa",                               "Delete",                            "Xóa"),
    OK                      ("OK",                               "OK",                                "OK",                                "OK"),

    // ── Navigation ────────────────────────────────────────────────────────────────
    BACK_TO_HOME            ("Back to Dashboard",                "Quay lại trang chủ",                "Back to Dashboard",                 "Quay lại trang chủ"),
    BACK_TO_HOME1           ("Back to Home",                     "Quay lại trang chủ",                "Back to Home",                      "Quay lại trang chủ"),
    BACK_TO_HOME2           ("Back to home page",                "Quay lại trang chủ",                "Back to home page",                 "Quay lại trang chủ"),
    BACK_HOME               ("Back home",                        "Về trang chủ",                      "Back to home page",                 "Về trang chủ"),
    BACK_TO_TRANSACTION     ("Back to Transactions",             "Về trang giao dịch",                "Back to Transactions",              "Về trang giao dịch"),
    BACK_TO_TRANSACTION_PAGE("Back to transaction page",         "Về trang giao dịch",                "Back to transaction page",          "Về trang giao dịch"),

    // ── Feature ───────────────────────────────────────────────────────────────────
    TRANSACTION             ("Transactions",                     "Giao dịch",                         "Transactions",                      "Giao dịch"),
    PAYMENT                 ("Payment",                          "Thanh toán",                        "Payment",                           "Thanh toán"),
    SAVINGS                 ("Savings",                          "Tiết kiệm",                         "Savings",                           "Tiết kiệm"),
    LOANS                   ("Loans",                            "Vay tiêu dùng",                     "Loans",                             "Vay tiêu dùng"),
    DISCOVERY               ("Discovery",                        "Khám phá",                          "Discovery",                         "Khám phá"),
    TRANSFER_REQUEST        ("Transfer request",                 "Nhắc chuyển tiền",                  "Transfer request",                  "Nhắc chuyển tiền"),
    SPLIT_BILL              ("Split bill",                       "Chia hóa đơn",                      "Split bill",                        "Chia hóa đơn"),
    SEND_GIFT               ("Send gift",                        "Trao yêu thương",                   "Send gift",                         "Trao yêu thương"),
    DOMESTIC_TRANSFER       ("Domestic transfer",                "Chuyển tiền",                       "Domestic transfer",                 "Chuyển tiền"),

    // ── Account ───────────────────────────────────────────────────────────────────
    LINK_CURRENT_ACCOUNT    ("Link Sacombank Card/Account",      "Liên kết thẻ/TK Sacombank",         "Link Sacombank Card/Account",        "Liên kết thẻ/ TK Sacombank"),
    LINK_CURRENT_ACCOUNT_1  ("Link Sacombank Card/Account",      "Thêm thẻ/ TK Sacombank",            "Link Sacombank Card/Account",        "Thêm thẻ/ TK Sacombank"),
    ADD_CURRENT_ACCOUNT     ("Add Sacombank Card/Account",       "Liên kết Thẻ/TK Sacombank",         "Add Sacombank card/account",         "Liên kết Thẻ/TK Sacombank"),
    CHANGE_ACCOUNT          ("Change account",                   "Đổi tài khoản",                     "Change account",                    "Đổi tài khoản"),
    PAYMENT_ACCOUNT         ("Payment account",                  "Tài khoản thanh toán",              "Payment account",                   "Tài khoản thanh toán"),
    SELECT_CARD_ACCOUNT     ("Select card/account",              "Chọn thẻ/tài khoản",                "Select card/account",               "Chọn thẻ/tài khoản"),

    // ── Input ─────────────────────────────────────────────────────────────────────
    ENTER_OTP               ("Enter OTP",                        "Nhập OTP",                          "Enter OTP",                         "Nhập OTP"),
    ENTER_AMOUNT            ("Enter amount",                     "Nhập số tiền",                      "Enter amount",                      "Nhập số tiền"),
    TXT_PHONE               ("Enter phone number",               "Nhập số điện thoại",                "Enter phone number",                "Nhập số điện thoại"),
    TXT_PASSWORD            ("Password",                         "Mật khẩu",                          "Password",                          "Mật khẩu"),
    ENTER_DESCRIPTION       ("Enter description",                "Nhập lời nhắn",                     "Enter description",                 "Nhập lời nhắn"),
    ENTER_DESCRIPTION_1     ("Enter description",                "Nhập diễn giải",                    "Enter description",                 "Nhập diễn giải"),

    // ── Message / Alert ───────────────────────────────────────────────────────────
    TRANSACTION_SUCCESSFUL  ("Transaction successful",           "Giao dịch thành công",              "Transaction successful",            "Giao dịch thành công"),
    PAYMENT_SUCCESSFUL      ("Payment\nsuccessful",              "Thanh toán\nthành công",             "Payment\nsuccessful",               "Thanh toán\nthành công"),
    TRANSFER_MONEY_SUCCESS  ("Transfer\nsuccessful",             "Chuyển tiền\nthành công",            "Transfer",                          "Chuyển tiền"),
    CANCEL_TRANSACTION      ("Do you want to cancel the ongoing transaction?", "Quý khách muốn huỷ giao dịch đang\nthực hiện?", "Do you want to cancel the ongoing transaction?", "Quý khách muốn huỷ giao dịch đang thực hiện?"),
    BIOMETRIC_LOGIN         ("Log in with biometrics",           "Đăng nhập bằng sinh trắc học",      "Log in with biometrics",            "Đăng nhập bằng sinh trắc học"),
    SUCCESSFUL              ("Successful",                       "Thành công",                        "successful",                        "Thành công"),
    UPDATE_SUCCESSFUL       ("Update successful",                "Cập nhật thành công",               "Update",                            "Cập nhật"),
    ONLINE_DEPOSIT_SETTLE   ("Settle",                           "Tất toán",                          "Settle",                            "Tất toán"),

    // ── Fields ─────────────────────────────────────────────────────────────────────
    ;

    private final String androidEng;
    private final String androidVn;
    private final String iOSEng;
    private final String iOSVn;

    Keyword(String androidEng, String androidVn, String iOSEng, String iOSVn) {
        this.androidEng = androidEng;
        this.androidVn  = androidVn;
        this.iOSEng     = iOSEng;
        this.iOSVn      = iOSVn;
    }

    public String androidEng() { return androidEng; }
    public String androidVn()  { return androidVn;  }
    public String iOSEng()     { return iOSEng;     }
    public String iOSVn()      { return iOSVn;      }
}
