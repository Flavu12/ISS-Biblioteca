namespace Iss_Lab1
{
    partial class Form5
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            listaCartiDisponibile = new ListBox();
            Logout = new Button();
            Imprumuta = new Button();
            Returneaza = new Button();
            listaCartiInProcesDeReturnare = new ListBox();
            SuspendLayout();
            // 
            // listaCartiDisponibile
            // 
            listaCartiDisponibile.FormattingEnabled = true;
            listaCartiDisponibile.ItemHeight = 15;
            listaCartiDisponibile.Location = new Point(29, 53);
            listaCartiDisponibile.Name = "listaCartiDisponibile";
            listaCartiDisponibile.Size = new Size(288, 349);
            listaCartiDisponibile.TabIndex = 0;
            // 
            // Logout
            // 
            Logout.Location = new Point(814, 484);
            Logout.Name = "Logout";
            Logout.Size = new Size(136, 45);
            Logout.TabIndex = 1;
            Logout.Text = "Logout";
            Logout.UseVisualStyleBackColor = true;
            // 
            // Imprumuta
            // 
            Imprumuta.Location = new Point(88, 425);
            Imprumuta.Name = "Imprumuta";
            Imprumuta.Size = new Size(174, 40);
            Imprumuta.TabIndex = 2;
            Imprumuta.Text = "Imprumuta";
            Imprumuta.UseVisualStyleBackColor = true;
            // 
            // Returneaza
            // 
            Returneaza.Location = new Point(528, 427);
            Returneaza.Name = "Returneaza";
            Returneaza.Size = new Size(192, 38);
            Returneaza.TabIndex = 3;
            Returneaza.Text = "Returneaza";
            Returneaza.UseVisualStyleBackColor = true;
            // 
            // listaCartiInProcesDeReturnare
            // 
            listaCartiInProcesDeReturnare.FormattingEnabled = true;
            listaCartiInProcesDeReturnare.ItemHeight = 15;
            listaCartiInProcesDeReturnare.Location = new Point(449, 53);
            listaCartiInProcesDeReturnare.Name = "listaCartiInProcesDeReturnare";
            listaCartiInProcesDeReturnare.Size = new Size(321, 349);
            listaCartiInProcesDeReturnare.TabIndex = 4;
            // 
            // Form5
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(968, 548);
            Controls.Add(listaCartiInProcesDeReturnare);
            Controls.Add(Returneaza);
            Controls.Add(Imprumuta);
            Controls.Add(Logout);
            Controls.Add(listaCartiDisponibile);
            Name = "Form5";
            Text = "Form5";
            ResumeLayout(false);
        }

        #endregion

        private ListBox listaCartiDisponibile;
        private Button Logout;
        private Button Imprumuta;
        private Button Returneaza;
        private ListBox listaCartiInProcesDeReturnare;
    }
}